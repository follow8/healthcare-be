package com.ownfollow.healthcare.service.impl;

import com.ownfollow.healthcare.web.dto.LoginDto;

public interface AuthenticationService {
  String authenticate(LoginDto loginDto);
}
