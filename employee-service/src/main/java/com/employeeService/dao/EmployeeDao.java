package com.employeeService.dao;

import org.employee.bo.EmployeeBo;
import org.employee.model.UpdateEmployeeRequest;
import java.util.List;

public interface EmployeeDao {
  EmployeeBo createEmployee(EmployeeBo employeeBo);

  EmployeeBo getEmployee(String id);

  List<EmployeeBo> listEmployees(int pageSize, int pageNumber);

  EmployeeBo updateEmployee(String id, UpdateEmployeeRequest employeeRequest);

  void deleteEmployee(String id);
}
