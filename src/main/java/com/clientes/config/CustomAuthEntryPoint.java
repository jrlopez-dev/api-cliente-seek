package com.clientes.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        Throwable cause = authException.getCause();
        if (cause != null && cause.getMessage().contains("JWT expired")) {
            throw new IllegalArgumentException("\"{\\\"error\\\": \\\"Token expirado. Por favor, obtén uno nuevo.\\\"}\")");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Acceso no autorizado. Token inválido o ausente.\"}");
        }
    }
}
