package com.venti.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenUtilTest {
    @Test
    public void token() throws Exception {
        String token = TokenUtil.token("test");
        System.out.println(token);
        System.out.println(BASE64Util.decode(token));
    }

}