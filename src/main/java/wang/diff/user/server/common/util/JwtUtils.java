package wang.diff.user.server.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import wang.diff.user.server.common.constants.SecurityConstants;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Slf4j
public class JwtUtils {

    public static String createToken(String userId, List<String> role) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes());
        String token = Jwts.builder()
                .setHeaderParam("AUT", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER) // iss: 该JWT的签发者，是否使用是可选的；
                .setSubject(userId) // sub: 该JWT所面向的用户，是否使用是可选的；
                .setAudience(SecurityConstants.TOKEN_AUDIENCE) // aud: 接收该JWT的一方，是否使用是可选的；
                .setIssuedAt(new Date()) // iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
                .setExpiration(new Date(System.currentTimeMillis() + (SecurityConstants.TOKEN_EXPIRATION * 1000)) ) // exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
                .claim("role", role)
                .signWith(secretKey).compact();
        return SecurityConstants.TOKEN_PREFIX + token;
    }

}
