package com.project.project6.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import com.project.project6.interceptor.AuthCheckInterceptor;

import java.util.List;

@Configuration
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // when static resources are inside resources folder under WEB-INF
        // registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");

        // when static resources are inside static folder under webapp
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/user/**");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/board/step*");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/board/update/**");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/board/delete/**");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/board/myList");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/board/favourite");
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/message/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("message.label");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public AuthCheckInterceptor authCheckInterceptor() {
        return new AuthCheckInterceptor();
    }

}