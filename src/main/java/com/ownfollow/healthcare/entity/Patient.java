package com.ownfollow.healthcare.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
  @Id
  private Long id;
  @OneToOne
  @JoinColumn(name = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_patient_to_account"))
  @MapsId
  private Account account;
  @Column(name = "info_path")
  private String infoPath;
  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "bloodType", column = @Column(name = "blood_type")),
          @AttributeOverride(name = "rhFactor", column = @Column(name = "rh_factor"))
  })
  private BloodType bloodType;
}
