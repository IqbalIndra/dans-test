package com.dans.test.demo.utils;

import com.dans.test.demo.model.User;
import com.dans.test.demo.exception.CredentialsException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    private static final long EXPIRE_DURATION =  60 * 60 * 1000; // 1 hour

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer("dans")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired ", ex.getMessage());
            throw new CredentialsException("Expired Token");
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace", ex.getMessage());
            throw new CredentialsException("Token was null");
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid", ex);
            throw new CredentialsException("Invalid Token");
        } catch (UnsupportedJwtException ex) {
            log.error("JWT is not supported", ex);
            throw new CredentialsException("Token Not Supported");
        } catch (SignatureException ex) {
            log.error("Signature validation failed");
            throw new CredentialsException("Token Signature failed");
        }
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
