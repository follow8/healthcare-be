package com.ownfollow.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String name;
  @OneToMany(mappedBy = "parent")
  private List<EmployeeInfo> child = new ArrayList<>();
  @ManyToOne
  @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_employee_info_to_parent"))
  private EmployeeInfo parent;
  @ManyToOne
  @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_employee_info_to_employee"))
  private Employee employee;
}
