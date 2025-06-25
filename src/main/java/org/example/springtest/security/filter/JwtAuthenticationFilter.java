package org.example.springtest.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) { //빈문자열인지, 토큰을 가지고 왔고, 가지고 있는 사람이라면 패스하면 된다 -> bearer
            String token = header.substring(7);
            //내일!
        }

        filterChain.doFilter(request, response);
    }
}
