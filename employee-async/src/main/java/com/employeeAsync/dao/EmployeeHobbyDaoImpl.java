package com.employeeAsync.dao;

import org.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeHobbyDaoImpl implements EmployeeHobbyDao {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public void setEmployeeHobby(int status, String empId) {
    employeeRepository.setHobbyStatus(1, empId);
  }
}
