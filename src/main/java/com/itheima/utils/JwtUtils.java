package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //密钥
    private static String signKey = "itheima";
    //过期时间
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param Claims
     * @return
     */
    public static String generateJwt(Map<String, Object> Claims){

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .addClaims(Claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();

        return jwt;
    }

    /**
     * 解析Jwt令牌
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt){

        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }


}
