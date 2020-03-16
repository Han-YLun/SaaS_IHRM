package com.ihrm.gate.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author: hyl
 * @date: 2020/01/26
 **/
public class ParseJwtTest {

    /**
     * 解析jwtToken字符串
     * @param args
     */
    public static void main(String[] args) {
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODAwMjI2OTcsImNvbXBhbnlJZCI6IjEyMzQ1NiIsImNvbXBhbnlOYW1lIjoi5rGf6IuP5pm66IGU6IKh5Lu95pyJ6ZmQ5YWs5Y-4In0.d8RuUwdIDUFGBhfXZUW6ypLLjG2Zh5O_RLU9gdq1gtw";
        Claims claims = Jwts.parser().setSigningKey("ihrm").parseClaimsJws(token).getBody();

        //私有数据存放在claims对象中
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

        //解析自定义claim的内容
        String companyId = (String) claims.get("companyId");
        String companyName = (String) claims.get("companyName");

        System.out.println(companyId + "    -----   " + companyName);

    }
}
