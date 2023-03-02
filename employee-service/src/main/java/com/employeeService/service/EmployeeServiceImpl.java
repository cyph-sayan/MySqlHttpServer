package com.employeeService.service;

import org.employee.bo.EmployeeBo;
import com.employeeService.dao.EmployeeDao;
import org.employee.mapper.EmployeeMapper;
import org.employee.model.CreateEmployeeRequest;
import org.employee.model.EmployeeHobby;
import org.employee.model.UpdateEmployeeRequest;
import org.employee.utility.IdGenerator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeMapper employeeMapper;
  @Autowired
  EmployeeDao employeeDao;

  @Override
  public EmployeeBo createEmployee(final CreateEmployeeRequest createEmployeeRequest) {
    String id=IdGenerator.generateEmployeeId();
    RestTemplate restTemplate=new RestTemplate();
    String uri="http://localhost:9090/api/v2/employees";
    EmployeeHobby employeeHobby=new EmployeeHobby();
    employeeHobby.setEmpId(id);
    employeeHobby.setHobby(createEmployeeRequest.getHobby());
    String hobbyResponse=restTemplate.postForObject(uri,employeeHobby,String.class);
    final EmployeeBo employeeBo =
        employeeMapper.createRequestToBo(createEmployeeRequest,id );
    return employeeDao.createEmployee(employeeBo);
  }

  @Override
  public EmployeeBo getEmployee(final String id) {
    return employeeDao.getEmployee(id);
  }

  @Override
  public List<EmployeeBo> listEmployees(final int pageSize, final int pageNumber) {
    return employeeDao.listEmployees(pageSize, pageNumber);
  }

  @Override
  public EmployeeBo updateEmployee(final String id, final UpdateEmployeeRequest request) {
    return employeeDao.updateEmployee(id, request);
  }

  @Override
  public void deleteEmployee(final String id) {
    employeeDao.deleteEmployee(id);
  }

}
