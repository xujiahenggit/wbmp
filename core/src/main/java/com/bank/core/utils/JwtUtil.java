package com.bank.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bank.core.entity.TokenUserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    /**
     * 过期时间60分钟
     */
    public static final long EXPIRE_TIME = 60 * 1000;
//    public static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, TokenUserInfo userInfo, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userId", userInfo.getUserId())
                    .withClaim("userName", userInfo.getUserName())
                    .withClaim("orgId", userInfo.getOrgId())
                    .withClaim("orgName", userInfo.getOrgName())
                    .build();

            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info(jwt + ":-token is valid");
            return true;
        } catch (Exception e) {
            log.info("The token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static TokenUserInfo getUserInfo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return TokenUserInfo.builder().userId(jwt.getClaim("userId").asString())
                    .userName(jwt.getClaim("userName").asString())
                    .orgId(jwt.getClaim("orgId").asString())
                    .orgName(jwt.getClaim("orgName").asString())
                    .build();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(TokenUserInfo userInfo, String secret) {
        Date date = new Date(System.currentTimeMillis());
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带用户信息信息
        return JWT.create()
                .withClaim("userId", userInfo.getUserId())
                .withClaim("userName", userInfo.getUserName())
                .withClaim("orgId", userInfo.getOrgId())
                .withClaim("orgName", userInfo.getOrgName())
                .withClaim("time", date)
//                .withExpiresAt(date)
                .sign(algorithm);
    }
}
