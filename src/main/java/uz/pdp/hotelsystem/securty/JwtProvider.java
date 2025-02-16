package uz.pdp.hotelsystem.securty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.key}")
    private String jwtSecretKey;

    public String generateToken(String username, Date expiration) {
        //  todo JWT token
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());

        return Jwts.builder()
                .signWith(secretKey)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .compact();
    }

    public String validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

}
