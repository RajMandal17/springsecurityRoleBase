package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        logger.info("User logged in with authorities: {}", authorities);

        for (GrantedAuthority authority : authorities) {
            logger.info("Checking authority: {}", authority.getAuthority());
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                logger.info("Redirecting to /admin/home");
                response.sendRedirect("/admin/adm");
                return;
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                logger.info("Redirecting to /user/home");
                response.sendRedirect("/user/usr");
                return;
            }
        }
        logger.info("No relevant role found, redirecting to /home");
        response.sendRedirect("/home"); // Default page if no role matched
    }
}