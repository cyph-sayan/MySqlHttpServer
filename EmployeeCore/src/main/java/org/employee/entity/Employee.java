package org.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends MetaData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rowId")
  private int rowId;

  @Column(name = "empId", nullable = false, unique = true)
  private String empId;

  @Column(name = "name")
  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(name = "dob")
  private Date dob;

  @Column(name = "gender")
  private String gender;

  @Column(name = "hobby_created")
  private int hobby_created=0;
}