package org.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hobbies")
public class EmployeeHobby {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rowId")
  private int rowId;

  @Column(name = "empId", nullable = false, unique = true)
  private String empId;

  @Column(name = "hobby")
  private String hobby;

}