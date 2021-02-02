package com.hhelp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hhelp.anno.LoginToken;
import com.hhelp.anno.PassToken;
import com.hhelp.domain.User;
import com.hhelp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 22:59
 */

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果有passtoken注释，则不需要认证
        if(method.isAnnotationPresent(PassToken.class))
            if(method.getAnnotation(PassToken.class).required())
                return true;
        //是否需要验证
        if(method.isAnnotationPresent(LoginToken.class))
            if(method.getAnnotation(LoginToken.class).required()){
                if (token == null)
                    throw new RuntimeException("402" + "用户认证失败，请登录");
                // 获取 token 中的 userEmail
                String userEmail;
                try {
                    userEmail = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = new User();
                user.setEmail(userEmail);
                User u = userService.findUserByEmail(user);
                if(userService.findUserByEmail(user) == null)
                    throw new RuntimeException("用户授权失败，请重新登录");
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(u.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("401" + e.getMessage());
                }
                return true;
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
