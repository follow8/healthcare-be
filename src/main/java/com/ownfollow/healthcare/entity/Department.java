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
@Table(name = "department")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String name;
  @Default
  @OneToMany(mappedBy = "parent")
  private List<Department> child = new ArrayList<>();
  @ManyToOne
  @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_department_to_parent"))
  private Department parent;
  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "fk_department_to_owner"))
  private Account owner;
}
