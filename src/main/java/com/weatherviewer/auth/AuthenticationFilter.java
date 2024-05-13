package com.weatherviewer.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends GenericFilterBean {
    private final List<String> allowedURLs = List.of("/", "sign-in", "sign-up");
    private final SessionRepo sessionRepo;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession(false);

        boolean allowed = allowedURLs.stream().anyMatch(httpRequest.getRequestURI()::endsWith);
        if (!allowed) {
            if (httpSession != null && httpSession.getAttribute("sessionId") != null) {
                UUID sessionId = UUID.fromString(httpSession.getAttribute("sessionId").toString());
                allowed = sessionRepo.findById(sessionId).isPresent();
            }
        }
        if (allowed) {
            chain.doFilter(request, response);
        } else {
            log.info("Access forbidden for " + httpRequest.getContextPath());
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/sign-in");
        }
    }
}