package com.ownfollow.healthcare.web;

import com.ownfollow.healthcare.annotation.V1APIRestController;
import com.ownfollow.healthcare.security.JwtAuthenticationFilter;
import com.ownfollow.healthcare.service.impl.AuthenticationService;
import com.ownfollow.healthcare.web.dto.JwtToken;
import com.ownfollow.healthcare.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@V1APIRestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JwtToken> authenticate(@Valid @RequestBody LoginDto dto) {
    String token = authenticationService.authenticate(dto);
    HttpHeaders bearerHeader = new HttpHeaders();
    bearerHeader.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, JwtAuthenticationFilter.BEARER_PREFIX + token);
    return new ResponseEntity<>(new JwtToken(token), bearerHeader, HttpStatus.OK);
  }
}
