package com.venti.util;

public class TokenUtil {

    private static final String TOKEN_CONTENT = "user_id:USERID, time_stamp:TIMESTAMP";
    public static String token(String id) {
        Long timeStamp = System.currentTimeMillis();
        String token = TOKEN_CONTENT.replace("USERID", id).replace("TIMESTAMP", timeStamp.toString());
        return MD5Util.encode(token);
    }
}

class MD5Util {
    public static String encode(String token) {
        return token;
    }
}
