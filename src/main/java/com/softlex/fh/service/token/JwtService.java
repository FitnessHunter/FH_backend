package com.softlex.fh.service.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.softlex.fh.entity.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    public static final String JWT_CLAIM_EMAIL = "email";
    public static final String JWT_CLAIM_ID = "id";
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(User user) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("userInfo", Map.of(JWT_CLAIM_EMAIL, user.getEmail(), JWT_CLAIM_ID, user.getId()))
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
//            .withExpiresAt(new Date((new Date()).getTime() + 10000))
                .sign(Algorithm.HMAC256(secret));
    }

    public Map<String, Object> validateTokenAndRetrieveSubject(String token)
            throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userInfo").asMap();
    }
}
