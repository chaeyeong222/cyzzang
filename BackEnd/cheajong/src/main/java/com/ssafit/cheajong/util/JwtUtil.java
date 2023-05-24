package com.ssafit.cheajong.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

// 토큰 생성을 위한 클래스
@Component
public class JwtUtil {
	private static final String Salt = "ZZINCHAEJONG"; // 암호키

	public String createToken(Map<String, Object> claims) throws UnsupportedEncodingException {

		return Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT").addClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 36000000L)) // 임의로 30분의 시간을 생성해놨는데, 필요에 따라서 수정
				.signWith(SignatureAlgorithm.HS256, Salt.getBytes("UTF-8")).compact();
	}

	public void valid(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
			SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Jwts.parser().setSigningKey(Salt.getBytes("UTF-8")).parseClaimsJws(token);
	}
}
