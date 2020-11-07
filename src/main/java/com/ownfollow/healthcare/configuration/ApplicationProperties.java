package com.ownfollow.healthcare.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

  // Jwt config
  private Jwt jwt = new Jwt();

  public class Jwt {

    private String secretKey;
    private String base64Secret;
    private int expiration; // in minutes
    private int rememberMeExpiration;

    public Jwt() {
      //empty constructor
    }

    public String getSecretKey() {
      return secretKey;
    }

    public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
    }

    public int getExpiration() {
      return expiration;
    }

    public void setExpiration(int expiration) {
      this.expiration = expiration;
    }

    public String getBase64Secret() {
      return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
      this.base64Secret = base64Secret;
    }

    public int getRememberMeExpiration() {
      return rememberMeExpiration;
    }

    public void setRememberMeExpiration(int rememberMeExpiration) {
      this.rememberMeExpiration = rememberMeExpiration;
    }
  }

  public Jwt getJwt() {
    return jwt;
  }
}
