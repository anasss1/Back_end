import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "votre_clé_secrète";
    private final long JWT_EXPIRATION = 86400000; // 1 jour en millisecondes

    // Généra un token à partir du nom d'utilisateur
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Valider le token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Gestion des erreurs de validation
            return false;
        }
    }

    // Extraire le nom d'utilisateur du token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
