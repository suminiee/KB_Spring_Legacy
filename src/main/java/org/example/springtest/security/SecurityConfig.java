package org.example.springtest.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity //spring security 해당 legacy 프로젝트 위에서 돌아간다 선언하는 것과 같음
@RequiredArgsConstructor //의존성 주입
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter; //request body에 관한 정보는 브라우저가 encoding해주지 않기 때문에 이 설정을 해둬야함
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/member/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
                .antMatchers("//**").authenticated();
//                .anyRequest().authenticated();

        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
    }
}
