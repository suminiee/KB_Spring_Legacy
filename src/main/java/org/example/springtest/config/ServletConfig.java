package org.example.springtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {
        "org.example.springtest.controller",
        "org.example.springtest.exception",
        "org.example.springtest.weather.controller"
})
public class ServletConfig implements WebMvcConfigurer {
    //web과 통신에 관련된 Bean은 ServletConfig에서 등록합니다.
    //@Controller, @RestController
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        // Swagger UI 리소스를 위한 핸들러 설정
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // Swagger WebJar 리소스 설정
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // Swagger 리소스 설정
        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/v2/api-docs")
                .addResourceLocations("classpath:/META-INF/resources/");
    }



    // jsp view resolver 설정
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }
}
