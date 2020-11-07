package com.ownfollow.healthcare.entity;

import com.ownfollow.healthcare.enums.AuthorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Authority implements GrantedAuthority {
  @Id
  @Column
  @Enumerated(EnumType.STRING)
  private AuthorityType name;

  @Override
  public String getAuthority() {
    return name.name();
  }
}
