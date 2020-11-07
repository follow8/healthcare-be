package com.ownfollow.healthcare.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BloodType {
  @Column(nullable = false)
  private String bloodType;
  @Column(nullable = false)
  private boolean rhFactor;
}
