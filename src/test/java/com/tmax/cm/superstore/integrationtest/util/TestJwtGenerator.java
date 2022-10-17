package com.tmax.cm.superstore.integrationtest.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TestJwtGenerator {

    private Key secretKey;

    private String defaultEmail = "totw2018@naver.com";

    public TestJwtGenerator(String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generate() {

        String token = Jwts.builder().setSubject(this.defaultEmail)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;
    }

    public String generate(String userEmail) {

        String token = Jwts.builder().setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;
    }

    public String generateExpiredToken(String userEmail) {

        String token = Jwts.builder().setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() - 3600000))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;
    }
}
