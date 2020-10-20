package org.krithika.grocery.authenticationEntryPoint;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by administrator on 19/10/20.
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Customize Header and Response when no Basic Auth header is present in the request
        String authHeader = request.getHeader("Authorization");
        if ( authHeader == null || !authHeader.startsWith("Basic ") ) {
            response.addHeader("Error Details", "Missing Basic Auth Header");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed, missing Basic Authentication Header");
        } else if (authException instanceof InsufficientAuthenticationException) {
            response.addHeader("Error Details", "Insufficient Credentials Details");
            response.getWriter().write("Insufficient Credential Details");
        }
    }

}
