package com.venti.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {

    private static final String TOKEN_CONTENT = "user_id:USERID, time_stamp:TIMESTAMP";

    public static String token(String id) {
        Long timeStamp = System.currentTimeMillis();
        String token = TOKEN_CONTENT.replace("USERID", id).replace("TIMESTAMP", timeStamp.toString());
        return MD5Util.encode(token);
    }

    private String createJWT(String id) {
        //当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime() + 24 * 60 * 60 * 1000);
        return Jwts
                .builder()
                .setSubject(id)
                .setIssuedAt(now)
                .setIssuer("venti")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "MineMine")
                .compact();
    }
}

class MD5Util {
    public static String encode(String token) {
        return token;
    }
    public static String decode(String token) {
        return token;
    }
}
