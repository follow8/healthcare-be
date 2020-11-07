package com.ownfollow.healthcare.service;

import com.ownfollow.healthcare.security.JwtTokenProvider;
import com.ownfollow.healthcare.service.impl.AuthenticationService;
import com.ownfollow.healthcare.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService {
  private final JwtTokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authBuilder;

  @Override
  public String authenticate(LoginDto loginDto) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(), loginDto.getPassword());
    Authentication auth = authBuilder.getObject().authenticate(authToken);
    SecurityContextHolder.getContext().setAuthentication(auth);
    boolean rememberMe = Boolean.TRUE.equals(loginDto.getRememberMe());
    LocalDateTime time = LocalDateTime.now();
    return tokenProvider.generateToken(auth, rememberMe, time);
  }
}
