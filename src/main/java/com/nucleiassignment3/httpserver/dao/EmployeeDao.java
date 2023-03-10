package com.nucleiassignment3.httpserver.dao;

import com.nucleiassignment3.httpserver.bo.EmployeeBo;
import com.nucleiassignment3.httpserver.model.UpdateEmployeeRequest;
import java.util.List;

public interface EmployeeDao {
  EmployeeBo createEmployee(EmployeeBo employeeBo);

  EmployeeBo getEmployee(String id);

  List<EmployeeBo> listEmployees(int pageSize, int pageNumber);

  EmployeeBo updateEmployee(String id, UpdateEmployeeRequest employeeRequest);

  void deleteEmployee(String id);
}
