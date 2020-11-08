package com.zyc.cloud.search.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    private static final String secret="token!123ab";

    //生成token
    public static String createJwtToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60*60*2);
        //生成令牌
        JWTCreator.Builder builder = JWT.create();
        for(Map.Entry<String,String> entry : map.entrySet())
            builder.withClaim(entry.getKey(),entry.getValue());
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(secret));
    }

    //验证token合法性
    public static DecodedJWT verify(String token){
        //创建验证对象,验证通过则正常执行，不通过则会报错（抛出异常）
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }



    //    //获取token信息(前提是token验证通过）
//    public static DecodedJWT getTokenInfo(String token){
//        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
//    }
}
