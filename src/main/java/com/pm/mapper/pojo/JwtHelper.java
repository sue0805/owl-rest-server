package com.pm.mapper.pojo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtHelper {

    public static String getJWTTokenFromMember(int id, String nickname, String email) { // 로컬용
        String token = null;
        DecodedJWT jwt = null;
        String tokenSecret ="MapperProject";

        try {
            Long EXPIRATION_TIME = 1000L * 60L * 60L * 24L; // 만료시간 하루
            Date issuedAt = new Date();
            Date notBefore = new Date(issuedAt.getTime());
            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(nickname)
                    .withAudience("local")
                    .withClaim("id", id)
                    .withClaim("email", email)
                    .withNotBefore(notBefore)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e) {
            System.err.println("err: " + e);
        }
        return token;
    }

    public static String getJWTTokenFromMember(int id, String nickname, Enum identify) {
        String token = null;
        DecodedJWT jwt = null;
        String tokenSecret ="MapperProject";

        try {
            Long EXPIRATION_TIME = 1000L * 60L * 60L * 24L; // 만료시간 하루
            Date issuedAt = new Date();
            Date notBefore = new Date(issuedAt.getTime());
            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(nickname)
                    .withAudience(identify.toString())
                    .withClaim("id", id)
                    .withClaim("email", id + "@" + identify.toString())
                    .withNotBefore(notBefore)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e) {
            System.err.println("err: " + e);
        }
        return token;
    }
}
