package org.example.springtest.security.filter;

import lombok.RequiredArgsConstructor;
import org.example.springtest.security.jwt.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

        //기존에는 토큰을 무조건 헤더에 담아서 보내서 헤더에서 bearer 을 분리해 내는 걸로 토큰을 찾았음
//        if (header != null && header.startsWith("Bearer ")) { //빈문자열인지, 토큰을 가지고 왔고, 가지고 있는 사람이라면 패스하면 된다 -> bearer
//            String token = header.substring(7);
//
//
//        }

        String token = resolveToken(request);

        if (jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth); // 이 토큰을 갖고 있는 사용자 정보를 추가(인증 정보가 있는지 context를 꺼내서 쉽게 확인가능)
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        //헤더에 있으면 헤더에서 토큰 분리
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }

        //쿠키에 있으면 쿠키에서 토큰 분리
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
