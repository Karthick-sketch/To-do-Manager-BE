package com.karthick.todomanager.config;

import com.karthick.todomanager.dto.RequestMetaDto;
import com.karthick.todomanager.util.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JWTUtils jwtUtils;

    RequestMetaDto requestMetaDto;

    public JWTInterceptor(RequestMetaDto requestMetaDto) {
        this.requestMetaDto = requestMetaDto;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("authorization");
        if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))) {
            Claims claims = jwtUtils.validateJWT(auth);
            requestMetaDto.setId(Integer.parseInt(claims.getIssuer()));
        }
        return super.preHandle(request, response, handler);
    }
}
