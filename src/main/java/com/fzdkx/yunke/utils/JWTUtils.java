package com.fzdkx.yunke.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fzdkx.yunke.bean.dao.TUser;

import java.util.HashMap;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
public class JWTUtils {

    public static final String SECRET = "6!#d%j(>_!fv^>:{}#!day&sca#";

    // 生成jwt
    public static String createJWT(String json) {
        // 组装头部
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                // 设置头部
                .withHeader(map)
                // 设置自定义负载
                .withClaim("user", json)
                // 设置签名
                .sign(Algorithm.HMAC256(SECRET));
    }

    // 解析jwt
    public static boolean verifyJWT(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwtVerifier.verify(jwt);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    // 获取jwt中的 user数据
    public static TUser getUser(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            String json = decodedJWT.getClaim("user").asString();
            return JSONUtils.toBean(json, TUser.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
