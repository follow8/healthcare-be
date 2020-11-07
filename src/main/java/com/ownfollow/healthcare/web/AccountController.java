package com.ownfollow.healthcare.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {
  private final AuthenticationService authenticationService;

  @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JwtToken> authenticate(@Valid @RequestBody LoginDto dto) {
    String token = authenticationService.authenticate(dto);
    HttpHeaders bearerHeader = new HttpHeaders();
    bearerHeader.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + token);
    return new ResponseEntity<>(new JwtToken(token), bearerHeader, HttpStatus.OK);
  }
}
