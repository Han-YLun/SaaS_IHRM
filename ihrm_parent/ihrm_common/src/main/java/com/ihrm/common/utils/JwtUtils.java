package com.ihrm.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/01/27
 **/
@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    //签名私钥
    private String key;
    //签名的失效时间
    private Long ttl;


    /**
     * 设置认证token
     * @param id    登录用户id
     * @param name  登录用户名
     * @param map   需要设置的内容
     * @return
     */
    public String createJwt(String id, String name, Map<String,Object> map){

        //设置失效时间
        long now = System.currentTimeMillis();//当前毫秒数
        long exp = now + ttl;
        //创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //根据map设置claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey() ,entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //创建token
        String token = jwtBuilder.compact();
        return token;
    }


    /**
     * 解析token字符串,获取clamis
     * @param token 需要解析的token字符串
     * @return
     */
    public Claims parseJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }
}
