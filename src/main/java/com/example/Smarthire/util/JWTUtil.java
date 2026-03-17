package com.example.Smarthire.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTUtil {
    private static String secret = System.getenv("SECRET_KEY");

    public static String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60)).signWith(SignatureAlgorithm.HS256 , secret).compact();
    }

    public static String extractUsernameFromToken(String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public static Boolean validateToken(String token , String username){
        String user = extractUsernameFromToken(token);
        return user.equals(username);
    }
}

