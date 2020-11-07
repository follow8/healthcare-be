package com.ownfollow.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column(name = "commentary")
  private String commentary;
  @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
  private List<Booking> bookings;
  @ManyToOne
  @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_schedule_to_employee"))
  private Employee employee;
}
