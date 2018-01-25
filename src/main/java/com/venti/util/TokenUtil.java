package com.venti.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TokenUtil {

    private static final String TOKEN_CONTENT = "user_id:USERID, time_stamp:TIMESTAMP";

    /**
     * 根据用户ID生成使用Base64加密的Token
     * @param id
     * @return
     */
    public static String token(String id) {
        Long timeStamp = System.currentTimeMillis();
        String token = TOKEN_CONTENT.replace("USERID", id).replace("TIMESTAMP", timeStamp.toString());
        return BASE64Util.encode(token);
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

class BASE64Util {
    /**
     * 使用Base64对字符串进行编码
     * @param str
     * @return
     */
    public static String encode(String str) {
        // BASE64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String result = null;
        try {
            result = encoder.encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对使用Base64编码的字符串解码
     * @param str
     * @return
     */
    public static String decode(String str) {
        // BASE64解码
        BASE64Decoder decoder = new BASE64Decoder();
        String result = null;
        try {
            result = new String(decoder.decodeBuffer(str), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
