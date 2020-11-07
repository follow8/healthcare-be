package com.ownfollow.healthcare.web;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ownfollow.healthcare.HealthcareApplication;
import com.ownfollow.healthcare.security.JwtTokenProvider;
import com.ownfollow.healthcare.util.JsonMapper;
import com.ownfollow.healthcare.web.dto.JwtToken;
import com.ownfollow.healthcare.web.dto.LoginDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HealthcareApplication.class)
@AutoConfigureMockMvc
public class JwtAuthenticationTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private JwtTokenProvider tokenProvider;

  public JwtAuthenticationTest() {
      //default constructor
  }

  @Test
  public void authenticateWithCorrectUsernameAndPassword() throws Exception {
    LoginDto dto = new LoginDto();
    dto.setUsernameOrEmail("user");
    dto.setPassword("*Password999*");
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
        .post("/api/authenticate")
        .content(JsonMapper.objAsJsonString(dto))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andReturn();
    String content = result.getResponse().getContentAsString();
    JwtToken jwtToken = JsonMapper.jsonStringAsObj(content, JwtToken.class);
    Assert.assertTrue(this.tokenProvider.validateToken(jwtToken.getValue()));
  }
  
  @Test
  public void authenticateWithCorrectEmailAndPassword() throws Exception {
    LoginDto dto = new LoginDto();
    dto.setUsernameOrEmail("user@email.com");
    dto.setPassword("*Password999*");
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
        .post("/api/authenticate")
        .content(JsonMapper.objAsJsonString(dto))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andReturn();
    String content = result.getResponse().getContentAsString();
    JwtToken token = JsonMapper.jsonStringAsObj(content, JwtToken.class);
    Assert.assertTrue(this.tokenProvider.validateToken(token.getValue()));
  }

  @Test
  public void authenticateWithIncorrectCredentials() throws Exception {
    LoginDto dto = new LoginDto();
    dto.setUsernameOrEmail("incorrectUsername");
    dto.setPassword("*incorectPass9*");
    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/api/authenticate")
        .content(JsonMapper.objAsJsonString(dto))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
