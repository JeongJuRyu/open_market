/*package com.tmax.cm.superstore.common.config.security;

import java.time.Instant;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tmax.cm.superstore.user.entities.User;

@Component
public class JWTUtil {
	private static long AUTH_TIME = 10;
	private static long REFRESH_TIME = 60 * 60 * 24 * 7;
	private static String SECRET_KEY;

	public static final String BEARER_WITH_SPACE = "Bearer ";
	public static final String BAERER = "Bearer";

	@Value("$jwt.secret")
	public void setSecretKey(String value) { SECRET_KEY = value;}

	private static Algorithm getAlgorithm(){
		byte[] key = Base64.getDecoder().decode(SECRET_KEY);
		return Algorithm.HMAC512(key);
	}

	public static String createAccessToken(User user){
		return JWT.create().withSubject(user.getUsername())
			.withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
			.sign(getAlgorithm());
	}
}
*/