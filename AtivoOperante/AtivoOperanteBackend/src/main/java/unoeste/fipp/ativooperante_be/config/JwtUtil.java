package unoeste.fipp.ativooperante_be.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtUtil {
    private static final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "MINHACHAVESECRETA_MINHACHAVESECRETA".getBytes(StandardCharsets.UTF_8));

    static public String getToken(String usuario,String nivel)
    {
        String jwtToken = Jwts.builder()
                .setSubject(usuario)
                .setIssuer("localhost:8080")
                .claim("nivel", nivel)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusDays(2L)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(CHAVE)
                .compact();
        return jwtToken;
    }

    static public boolean verifyToken(String token)
    {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return false;
    }

    static public Claims getAllClaimsFromToken(String token)
    {
        Claims claims=null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar as informações (claims)");
        }
        return claims;
    }

    public static String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            System.out.println("Error validating token: " + e.getMessage());
            return null;
        }
    }
}