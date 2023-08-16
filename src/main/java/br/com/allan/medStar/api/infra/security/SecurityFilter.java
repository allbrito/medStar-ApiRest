package br.com.allan.medStar.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperToken(request);
        var subject = tokenService.getSubject(tokenJWT);
        filterChain.doFilter(request, response);
    }

    private String recuperToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        return (authorizationHeader != null) ? authorizationHeader.replace("Bearer ", "") : null;
    }
}
