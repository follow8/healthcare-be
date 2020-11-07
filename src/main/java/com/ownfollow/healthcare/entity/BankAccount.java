package com.ownfollow.healthcare.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private BigDecimal balance;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @OneToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_bank_account_to_account"))
  private Account account;
}
