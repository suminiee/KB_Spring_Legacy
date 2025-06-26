package org.example.springtest.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtest.dto.user.LoginRequestDto;
import org.example.springtest.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager; //spring security에서 제공
    private final JwtTokenProvider jwtTokenProvider;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        setFilterProcessesUrl("/auth/login"); // 로그인 경로
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginRequestDto loginRequest = mapper.readValue(request.getInputStream(),
                    LoginRequestDto.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new AuthenticationServiceException("로그인 요청 실패");
        }
    }

    //위에가 성공하면 아래애가 바로 호출 -> 토큰 만들어서 이름이랑 권한을 넣고 response에 넣어서 전달해줌
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        String role = authResult.getAuthorities().iterator().next().getAuthority();

        String token = jwtTokenProvider.createToken(authResult.getName(), Arrays.asList(role));

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");

        String responseBody = String.format("{\"token\":\"%s\", \"role\":\"%s\"}", token, role);

        response.getWriter().write(responseBody);
    }
}
