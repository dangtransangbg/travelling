package travelling.api.security;


import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import travelling.api.security.jwt.TokenConsumer;
import travelling.api.security.jwt.model.JwtPayload;

@Component
public class TokenAuthenticator {
    private TokenConsumer consumer;

    @Autowired
    public TokenAuthenticator(TokenConsumer consumer) {
        this.consumer = consumer;
    }

    public Authentication getAuthentication(String token) throws InvalidJwtException {
        UserAuthentication userAuthentication = null;
        if (token != null) {
            JwtPayload jwtPayload = this.consumer.consume(token);
            UserDetails userDetails = new UserDetails();
            userDetails.setUser(jwtPayload);
            userAuthentication = new UserAuthentication(userDetails);
        }

        return userAuthentication;
    }
}
