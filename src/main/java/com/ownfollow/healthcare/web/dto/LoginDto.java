package com.ownfollow.healthcare.web.dto;

import com.ownfollow.healthcare.annotation.ValidPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ownfollow.healthcare.web.validator.PasswordValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
  @Size(min = PasswordValidator.MIN_PASSWORD_LENGTH, max = PasswordValidator.MAX_PASSWORD_LENGTH)
  @NotBlank
  private String usernameOrEmail;
  @NotBlank
  @ValidPassword
  private String password;
  private Boolean rememberMe;
}
