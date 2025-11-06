package com.godiapps.places.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-ms}")
    private long expirationMS;

    @Value("${EXTRA_TIME_VALUE}")
    private long extraTimeExpiration;

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, Map<String, Object> extraClaims){
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMS);
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(now)
                .expiration(exp)
                .signWith(getKey())
                .compact();
    }

    public String generateLoginToken(String username, Map<String, Object> extraClaims){
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMS + extraTimeExpiration);
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(now)
                .expiration(exp)
                .signWith(getKey())
                .compact();
    }


    public boolean isTokenValid(String token, String username){
        try{
            return extractUsername(token).equals(username) && !isTokenExpired(token);
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpired(String token){
            Date exp = Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();
            return exp.before(new Date());
    }

    public boolean validateTokenUsername(String email, String token){
        try {
            return(email.equals(token));
        }catch (Exception exception){
            return false;
        }
    }
}
