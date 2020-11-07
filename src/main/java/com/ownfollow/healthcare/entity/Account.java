package com.ownfollow.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "account",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(name = "first_name", nullable = false)
  private String firstName;
  @Column(name = "last_name", nullable = false)
  private String lastName;
  @Column(name = "insurance_number")
  private String insuranceNumber;
  @Column
  private boolean active;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "img_path")
  private String imagePath;
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;
  @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
  private BankAccount bankAccount;
  @ManyToMany
  @JoinTable(
          name = "account_authority",
          joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_account_authority_to_user")),
          inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "name", foreignKey = @ForeignKey(name = "fk_account_authority_to_authority_name"))
  )
  @Default
  private Set<Authority> authorities = new HashSet<>();

  @OneToMany(mappedBy = "owner")
  @Default
  private List<Department> departmentList = new ArrayList<>();
}




