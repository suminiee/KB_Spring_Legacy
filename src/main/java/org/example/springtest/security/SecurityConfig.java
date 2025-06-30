package org.example.springtest.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.security.filter.JwtAuthenticationFilter;
import org.example.springtest.security.filter.JwtLoginFilter;
import org.example.springtest.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity //spring security 해당 legacy 프로젝트 위에서 돌아간다 선언하는 것과 같음
@RequiredArgsConstructor //의존성 주입
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter; //request body에 관한 정보는 브라우저가 encoding해주지 않기 때문에 이 설정을 해둬야함
    }

    //jwt용 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable();

        //토큰 여부만 보고 판단할거임
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//세션의 상태가 필요 없어졌음 -> 세션은 상태없음

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**", "/oauth/**").permitAll()
                .antMatchers("/auth/admin").hasRole("ADMIN") //hasRole을 쓰면 ROLE_안써도 됨 -> 시큐리티가 알아서 붙여서 비교함
                .antMatchers("/auth/member").hasAnyRole("ADMIN", "MEMBER")
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/**").authenticated();

        http.addFilterBefore(
                new JwtLoginFilter(authenticationManager(), jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );

    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/member/**", "/post/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
////                .antMatchers("//**").authenticated();
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                .loginPage("/user/login")
//                .loginProcessingUrl("/user/login")
//                .defaultSuccessUrl("/user/login-success")
//                .failureUrl("/user/login-failed");
//        http.logout()
//                .logoutUrl("/user/logout")
//                .invalidateHttpSession(true) //세션 죽인다
//                .deleteCookies("JSESSIONID", "remember-me")//jsessionid 삭제, 자동로그인 할 떄 만들어지는 쿠키도 삭제
//                .logoutSuccessUrl("/login")
//                .permitAll();
//
//        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
//
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //cors 회피
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");//여기까지는 설정

        source.registerCorsConfiguration("/**", config); //모든 주소에 대해 설정 적용
        return new CorsFilter(source);
    }
}
