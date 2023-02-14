package com.asraf.infosapp.config;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service	
public class JwtService {

	//256 bit hex secret key 
	private static final String SECRET_KEY = "576E5A7234753778214125442A472D4B6150645367556B58703273357638792F";

	public String extractUsername(String token) {	
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes =  Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}

	public String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails

			) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();

	}

	private boolean isTokenExpired(String token) {
		return extractExpirtation(token).before(new Date());
	}

	private Date extractExpirtation(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

}
