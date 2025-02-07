package com.example.user_service.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    private final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvPx7vRhoKCD79XB39gcLjp/ESKdvat6izONirIiaswLwSLLRhMdEpuekeqZ3Z+QBl94g5R6E/Kx7d8gHOEZeCZ6JXvwX6n7xb1ALtOSiE1v7n/VFgkmX7Jg4cmfFLIQFumktB3M1HRSYJAY32GEODU80RBwU6nqMdlSfh051myOjb6eEK/Y+fDicqetH9BFKwCr3hzjd6BZMgYF/V7LEQAEKofVk72BKR+g5EEHMb6gjbpGf9uc0w40tm90/7Pm1I7qRxRYWPcQOaki+VZBPCZT3O4e/XPhr8O3jtQE+hZAe3zLJs3DwTh08kjjnB1PQi5R24R9Z5oVzyE9lJWbt4wIDAQAB";


    public String extractKeycloakId(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }


    private Claims extractClaims(String token) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(publicKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            PublicKey key = keyFactory.generatePublic(keySpec);

            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            return claims;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }



}


