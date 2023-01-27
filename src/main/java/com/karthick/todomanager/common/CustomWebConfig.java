package com.karthick.todomanager.common;

import com.karthick.todomanager.config.JWTInterceptor;
import com.karthick.todomanager.dto.RequestMetaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomWebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
    }

    @Bean
    @RequestScope
    public RequestMetaDto getRequestMetaDto() {
        return new RequestMetaDto();
    }

    @Bean
    public JWTInterceptor setRequestMetaDto() {
        return new JWTInterceptor(getRequestMetaDto());
    }
}
