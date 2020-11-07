package com.ownfollow.healthcare.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
  @Id
  private Long id;
  @OneToOne
  @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_employee_to_account"))
  @MapsId
  //treats id as primary key and foreign key for account
  private Account account;
  @Column(name = "started_at")
  private LocalDateTime startedAt;
  @Column(name = "ended_at")
  private LocalDateTime endedAt;
  @Column(name = "degree")
  private String degree;
  @Column(name = "salary")
  private Integer salary;
  @ManyToMany
  @JoinTable(
      name = "cabinet_employee",
      joinColumns = {@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_cabinet_employee_to_employee"))},
      inverseJoinColumns = {@JoinColumn(name = "cabinet_id", foreignKey = @ForeignKey(name = "fk_cabinet_employee_to_cabinet"))}
  )
  private Set<Cabinet> cabinetList;
  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "bloodType", column = @Column(name = "blood_type")),
          @AttributeOverride(name = "rhFactor", column = @Column(name = "rh_factor"))
  })
  private BloodType bloodType;
  @Default
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Schedule> scheduleList = new ArrayList<>();
}
