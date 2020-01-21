package com.ihrm.demo;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: hyl
 * @date: 2020/01/21
 **/
public class CreateJwtTest {

    /**
     * 通过jjwt创建token
     * @param args
     */
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder().setId("888").setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ihrm");
        String token = jwtBuilder.compact();
        System.out.println(token);
    }
}
