package arthur.movie_database.security.jwt;


import arthur.movie_database.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userData){
        return Jwts.builder().setSubject(userData.getUsername())
                .issuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs)).signWith(getSignedKey(),
                        SignatureAlgorithm.HS512).compact();
    }

    public Key getSignedKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean valitadeJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(getSignedKey()).build().parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException e){
            System.out.println("token invalido " + e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.println("token expirado " + e.getMessage());
        }
        return false;
    }
}
