package com.practicaWipay.client_service.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String SECRET_KEY = "c2VjcmV0YXNlY3JldGFzZWNyZXRhc2VjcmV0YXNlY3JldGE="; // Clave secreta para firmar el token

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization"); // Obtener el header de autorización

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT no proporcionado o formato incorrecto");
            return false;
        }

        String token = authHeader.substring(7); // Eliminar "Bearer " y obtener solo el token

        try {
            // Decodificar JWT
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String name = claims.get("name", String.class);
            Integer age = claims.get("age", Integer.class);

            if (age == null || age < 18) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: Menor de edad");
                return false;
            }

            request.setAttribute("userName", name);

            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error en los logs
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT inválido: " + e.getMessage());
            return false;
        }
    }

}
