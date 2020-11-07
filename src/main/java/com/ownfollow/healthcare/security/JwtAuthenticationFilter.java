package com.ownfollow.healthcare.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  private JwtTokenProvider tokenProvider;

  public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void doFilter(ServletRequest req,
      ServletResponse resp, FilterChain filterChain)
      throws ServletException, IOException {
    String token = getJwtFromRequest(((HttpServletRequest) req));
    if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
      Authentication authentication = this.tokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(req, resp);
  }

  private String getJwtFromRequest(HttpServletRequest req) {
    String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    return null;
  }
}
