package com.weatherviewer.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
@Slf4j
public class AuthenticationFilter implements Filter {
    private final List<String> allowedURLs = List.of("/", "sign-in", "sign-up");

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession httpSession = httpRequest.getSession(false);

        boolean authenticated = httpSession != null && httpSession.getAttribute("sessionId") != null;

        if (authenticated || allowedURLs.stream().anyMatch(httpRequest.getRequestURI()::endsWith)) {
            chain.doFilter(request, response);
        } else {
            log.info("Access forbidden for " + httpRequest.getContextPath());
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/sign-in");
        }
    }

    @Override
    public void destroy() {
    }
}