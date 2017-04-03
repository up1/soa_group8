package room.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import room.model.JwtUser;

import javax.annotation.PostConstruct;
import java.util.Base64;

/**
 * Created by Adisorn on 3/4/2560.
 */
@Service
public class JwtService {

    @Value("${authentication.token.secret}")
    private String plainSecret;
    private String encodedSecret;

    @PostConstruct
    protected void init() {
        this.encodedSecret = generateEncodedSecret(this.plainSecret);
    }

    protected String generateEncodedSecret(String plainSecret)
    {
        if (StringUtils.isEmpty(plainSecret))
        {
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");
        }
        return Base64
                .getEncoder()
                .encodeToString(this.plainSecret.getBytes());
    }

    protected JwtUser getUser(String encodedSecret, String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();
        String userName = claims.getSubject();
        String role = (String) claims.get("role");
        JwtUser securityUser = new JwtUser();
        securityUser.setUsername(userName);
        securityUser.setRole(role);
        return securityUser;
    }

    public JwtUser getUser(String token)
    {
        return getUser(this.encodedSecret, token);
    }

}
