package com.weatherviewer.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements HandlerInterceptor {
    private final SessionRepo sessionRepo;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession(false);
        log.info("Checking access for " + request.getRequestURI());

        boolean allowed = false;
        if (httpSession != null && httpSession.getAttribute("sessionId") != null) {
            UUID sessionId = UUID.fromString(httpSession.getAttribute("sessionId").toString());
            allowed = sessionRepo.findById(sessionId).isPresent();
        }
        if (allowed) {
            log.info("Access allowed for " + request.getRequestURI());
            return true;
        }
        log.info("Access forbidden for " + request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/sign-in");
        return false;
    }
}