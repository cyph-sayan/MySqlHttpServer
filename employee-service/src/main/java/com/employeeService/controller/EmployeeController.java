package com.employeeService.controller;

import org.employee.bo.EmployeeBo;
import org.employee.model.CreateEmployeeRequest;
import org.employee.model.ListPageRequest;
import org.employee.model.UpdateEmployeeRequest;
import com.employeeService.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  @Autowired
  /* default */ EmployeeService employeeService;

  @PostMapping("/employees")
  public EmployeeBo addEmployee(@RequestBody final CreateEmployeeRequest createEmployeeRequest) {
    return employeeService.createEmployee(createEmployeeRequest);
  }

  @PutMapping("/employees/{id}")
  public EmployeeBo updateEmployee(@PathVariable final String id,
                                   @RequestBody final UpdateEmployeeRequest updateEmployeeRequest) {
    return employeeService.updateEmployee(id, updateEmployeeRequest);
  }

  @GetMapping("/employees")
  public List<EmployeeBo> listEmployees(@RequestBody final ListPageRequest listPageRequest) {
    return employeeService.listEmployees(listPageRequest.getPageSize(),
        listPageRequest.getPageNumber());
  }

  @GetMapping("/employees/{id}")
  public EmployeeBo getEmployee(@PathVariable final String id) {
    return employeeService.getEmployee(id);
  }

  @DeleteMapping("/employees/{id}")
  public void deleteEmployee(@PathVariable final String id) {
    employeeService.deleteEmployee(id);
  }

}
