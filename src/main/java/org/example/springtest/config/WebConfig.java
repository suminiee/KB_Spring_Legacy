package org.example.springtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                ServletConfig.class,
                RedisConfig.class, //여기(RedisConfig file)에 있는 설정 정보들을 레거시에 적용***
                SwaggerConfig.class //SwaggerConfig에 있는 설정 정보들을 레거시에 적용
        };
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴, / : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**"
        };
    }

    // 문자 인코딩 필터설정, UTF-8설정
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

    // Spring 이 직접 404을 발생 시키도록 설정을 추가
    // 예외가 발생하면 톰캣이 처리하는 게 아니라 스프링에서 직접 처리하도록 하는 설정
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

}
