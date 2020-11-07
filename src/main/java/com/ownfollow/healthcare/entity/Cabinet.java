package com.ownfollow.healthcare.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cabinet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String name;
  @Column
  private Integer floorNumber;
  @ManyToMany(mappedBy = "cabinetList")
  private List<Employee> employee = new ArrayList<>();
}
