package com.ownfollow.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "commentary")
  private String commentary;
  @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
  private Booking booking;
  @ManyToOne
  @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_appointment_to_employee"))
  private Employee employee;
  @ManyToOne
  @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_appointment_to_patient"))
  private Patient patient;
}
