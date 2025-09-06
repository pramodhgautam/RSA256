package com.nchl.rs256.service;

import com.nchl.rs256.util.KeyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Service
public class JwtService {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public JwtService() throws Exception {
        this.privateKey = KeyUtil.loadPrivateKey("src/main/resources/keys/private.pem");
        this.publicKey = KeyUtil.loadPublicKey("src/main/resources/keys/public.pem");
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("MyApp")
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
