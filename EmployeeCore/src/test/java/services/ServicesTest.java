package services;

import org.employee.service.EmployeeService;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

@Disabled
public class ServicesTest {
  @Autowired
  EmployeeService employeeService;

//  @Ignore
//  @Test
//  void createRequestTest() {
//    EmployeeBo employeeBo = employeeService.createEmployee(
//        new CreateEmployeeRequest("Syan", Date.valueOf("2000-08-11"), "M"));
//    Assertions.assertEquals("Syan", employeeBo.getName());
//    Assertions.assertEquals("2000-08-11", employeeBo.getDob());
//    Assertions.assertEquals("M", employeeBo.getGender());
//  }
}
