package com.ownfollow.healthcare.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtToken {
  @JsonProperty("token")
  private String value;

  public JwtToken(String value) {
    this.value = value;
  }
}
