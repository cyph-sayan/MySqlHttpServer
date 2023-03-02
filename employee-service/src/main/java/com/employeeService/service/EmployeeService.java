package com.employeeService.service;

import org.employee.bo.EmployeeBo;
import org.employee.model.CreateEmployeeRequest;
import org.employee.model.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

  EmployeeBo createEmployee(CreateEmployeeRequest createEmployeeRequest);

  EmployeeBo getEmployee(String id);

  List<EmployeeBo> listEmployees(int pageSize, int pageNumber);

  EmployeeBo updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest);

  void deleteEmployee(String id);

}
