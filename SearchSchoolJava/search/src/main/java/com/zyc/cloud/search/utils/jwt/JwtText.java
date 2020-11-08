package com.zyc.cloud.search.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;


public class JwtText{
    @Test
    public void createJwtToken(){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60*60*2);
        //生成令牌
        String token = JWT.create()
                .withClaim("username", "张三")//设置自定义用户名
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256("token!123ab"));//设置签名 保密 复杂
        //输出令牌
        System.out.println(token);
    }
    @Test
    public void freeJwt(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDQ3ODMwNzcsInVzZXJuYW1lIjoi5byg5LiJIn0.GO2nnR8V42kuSQC0d37JEpeyIeMXJjGfOyc1XVp296w";
        //创建验证对象
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("token!123ab")).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        System.out.println("用户名："+decodedJWT.getClaim("username").asString());
        System.out.println("过期时间；"+decodedJWT.getExpiresAt());
    }
}
