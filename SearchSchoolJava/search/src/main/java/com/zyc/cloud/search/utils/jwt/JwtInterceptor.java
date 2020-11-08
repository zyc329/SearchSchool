package com.zyc.cloud.search.utils.jwt;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map=new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("Access-Token");
        try{
            JwtUtils.verify(token);//验证令牌
            return true;
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","token验证失败，请重新登录！");
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSON.toJSON(map).toString());
        return false;
    }
}