package com.ownfollow.healthcare.entity;

import com.ownfollow.healthcare.enums.BookingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_time")
  private LocalDateTime startTime;

  @Column(name = "duration")
  private Integer duration;

  @Column(name = "booking_type")
  @Enumerated
  private BookingType bookingType;
  @OneToOne
  @JoinColumn(name = "appointment_id", foreignKey = @ForeignKey(name = "fk_booking_to_appointment"))
  private Appointment appointment;

  @ManyToOne
  @JoinColumn(name = "schedule_id", foreignKey = @ForeignKey(name = "fk_booking_to_schedule"))
  private Schedule schedule;
}
