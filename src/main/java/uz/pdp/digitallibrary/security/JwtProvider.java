package uz.pdp.digitallibrary.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.digitallibrary.entity.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {


    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expirationDate}")
    private Integer expirationDate;

    private final static int ONE_DAY_IN_MILLISECONDS = 86_400_000;



    public String generateToken(User user) {


        List<String> collect = user.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList();
        String role = collect.get(0);

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .signWith(key)
                .setSubject(user.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate*120_000))
                .compact();

    }

    public String validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);


        return claims.getBody().getSubject();

    }

}



