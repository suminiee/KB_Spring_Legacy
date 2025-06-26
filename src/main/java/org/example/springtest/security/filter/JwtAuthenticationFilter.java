package org.example.springtest.security.filter;

import lombok.RequiredArgsConstructor;
import org.example.springtest.security.jwt.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
//토큰 검증
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) { //빈문자열인지, 토큰을 가지고 왔고, 가지고 있는 사람이라면 패스하면 된다 -> bearer
            String token = header.substring(7);

            if (jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth); // 이 토큰을 갖고 있는 사용자 정보를 추가(인증 정보가 있는지 context를 꺼내서 쉽게 확인가능)
            }
        }

        filterChain.doFilter(request, response);
    }
}
