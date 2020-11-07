package com.ownfollow.healthcare.security;

import com.ownfollow.healthcare.configuration.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
  private static final String AUTHORITIES_KEY = "auth";
  private int expiration; // in minutes
  private int expirationForRememerMe; // in minutes
  private Key secretKey;
  private final ApplicationProperties.Jwt jwtProperties;

  public JwtTokenProvider(ApplicationProperties appProperties) {
    this.jwtProperties = appProperties.getJwt();
  }

  @PostConstruct
  public void init() {
    this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    this.expiration = jwtProperties.getExpiration();
    this.expirationForRememerMe = jwtProperties.getExpiration();
  }

  public String generateToken(Authentication auth, boolean rememberMe) {
      return generateToken(auth, rememberMe, LocalDateTime.now());
  }

  public String generateToken(Authentication auth, boolean rememberMe, LocalDateTime time) {
    String authorities = auth.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    if (rememberMe) {
      time = time.plusMinutes(expirationForRememerMe);
    } else {
        time = time.plusMinutes(expiration);
    }

    return Jwts.builder()
        .setSubject(auth.getName())
        .claim(AUTHORITIES_KEY, authorities)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(secretKey, SignatureAlgorithm.HS512)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody();
    List<? extends GrantedAuthority> auth = Arrays
        .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    User principal = new User(claims.getSubject(), "", auth);
    return new UsernamePasswordAuthenticationToken(principal, token, auth);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    } catch (SignatureException ex) {
      log.warn("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.warn("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.warn("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.warn("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.warn("JWT claims string is empty.");
    }
    return true;
  }
}
