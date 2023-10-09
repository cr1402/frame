package com.kejin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtils {
    // 签名密钥
    private static final String SECRET = "CXPT";

    /**
     * 生成token
     * @param id 根据用户id生成
     * @return 返回token
     */
    public String getToken(String id){
        Calendar instance = Calendar.getInstance();//获取当前时间
        instance.add(Calendar.HOUR_OF_DAY, 2);//过期时间

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        builder.withClaim("userId",id);
        // 指定过期时间和签名算法
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET));
        return token;

    }

    /**
     * 验证token,并重置token有效时间
     * @param token
     * @return
     */
    public boolean verify(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);// 如果验证通过，则不会报错，否则会抛出异常
        Date date = verify.getExpiresAt();
        Calendar old = Calendar.getInstance();
        old.setTime(date);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int num =now.get(Calendar.MINUTE) - old.get(Calendar.MINUTE);
        return num <=15;
    }

    /**
     * 获取token中的用户id
     * @param token
     * @return
     */
    public String getTokenId(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim userId = decodedJWT.getClaim("userId");
        return userId.asString();

    }

}
