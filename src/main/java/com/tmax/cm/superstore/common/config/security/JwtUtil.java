package com.tmax.cm.superstore.common.config.security;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tmax.cm.superstore.error.exception.InvalidJwtException;
import com.tmax.cm.superstore.user.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	private static final long AUTH_TIME = 10;
	private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

	private static Key key;

	private static String secret;
	private static long tokenValidityInMilliseconds;

	private static final String AUTHORIZATION_HEADER = "Authorization";

	public JwtUtil(
			@Value("${jwt.secret}") String secret,
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
		JwtUtil.secret = secret;
		this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
	}

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	private static SignatureAlgorithm getAlgorithm() {
		return SignatureAlgorithm.HS512;
	}

	public static String createAccessToken(User user) {
		long now = (new Date()).getTime();
		Date validity = new Date(now + JwtUtil.tokenValidityInMilliseconds);
		return Jwts.builder().setSubject(user.getUsername())
				.claim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
				.signWith(key, getAlgorithm())
				.setExpiration(validity)
				.compact();
	}

	public static String createRefreshToken(User user) {
		long now = (new Date()).getTime();
		Date validity = new Date(now + JwtUtil.tokenValidityInMilliseconds);
		return Jwts.builder().setSubject(user.getUsername())
				.claim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
				.signWith(key, getAlgorithm())
				.setExpiration(validity)
				.compact();
	}

	public static String resolveToken(HttpServletRequest request) throws InvalidJwtException {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

		if (bearerToken == null) {
			throw new InvalidJwtException("Authorization header is null");
		}

		if (!bearerToken.startsWith("Bearer ")) {
			throw new InvalidJwtException("Authorization type must be 'Bearer'");
		}

		return bearerToken.substring(7);
	}

	public static VerifyResult validateToken(String token) throws InvalidJwtException {
		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return VerifyResult.builder().success(true).username(claimsJws.getBody().getSubject()).build();
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			logger.info("잘못된 JWT 서명입니다.");
			throw new InvalidJwtException("잘못된 Jwt 서명입니다");
		} catch (ExpiredJwtException e) {
			logger.info("만료된 JWT 토큰입니다.");
			throw new InvalidJwtException("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			logger.info("지원되지 않는 JWT 토큰입니다.");
			throw new InvalidJwtException("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			logger.info("JWT 토큰이 잘못되었습니다.");
			throw new InvalidJwtException("JWT 토큰이 잘못되었습니다.");
		}

		// return VerifyResult.builder().success(false).username(null).build();
	}

}
