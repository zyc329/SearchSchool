package com.zyc.cloud.search.utils.jwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zyc.cloud.search.admin.user.service.UserService;
import com.zyc.cloud.search.utils.jwt.JwtUtils;
import com.zyc.cloud.search.utils.jwt.model.UserLoginModel;
import com.zyc.cloud.search.utils.jwt.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userLogin")
@CrossOrigin
@Slf4j
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("login")
    public Map<String,Object> login(@RequestBody UserLoginModel userLoginModel){
        Map<String,Object> map = new HashMap<>();
        UserLoginModel userDB = userLoginService.login(userLoginModel);
        if(userDB!=null ){
            //认证成功后，生成JWT令牌
            if(userDB.getState() ==0){
                Map<String,String> payload = new HashMap<>();
                payload.put("account",userDB.getAccount());
                payload.put("role",userDB.getRole());
                String jwtToken = JwtUtils.createJwtToken(payload);
                map.put("flag",true);
                map.put("message","认证成功");
                map.put("token",jwtToken);//响应jwtToken
            }else{
                map.put("flag",false);
                map.put("message","你已被封号！");
            }
        }
        else{
            map.put("flag",false);
            map.put("message","账号密码错误！");
        }
        return map;
    }

    @PostMapping("getUserInfo")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String token = request.getHeader("Access-Token");
        try{
            DecodedJWT verify = JwtUtils.verify(token);
            String account = verify.getClaim("account").asString();
            UserLoginModel userLoginModel = userLoginService.gerUserInfo(account);
            map.put("userInfo",userLoginModel);
            map.put("flag",true);
            map.put("message","请求成功");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","请求失败");
        }
        return map;
    }

}
