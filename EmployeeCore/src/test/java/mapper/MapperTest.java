package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.employee.bo.EmployeeBo;
import org.employee.entity.Employee;
import org.employee.mapper.EmployeeMapper;
import org.employee.model.CreateEmployeeRequest;
import org.employee.utility.IdGenerator;
import java.sql.Date;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@Disabled
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {

  @Autowired
  public EmployeeMapper employeeMapper;

  @Test
  void testEntityToBo() {
    Employee employee = new Employee();
    employee.setName("Sayan");
    employee.setDob(Date.valueOf("2000-08-11"));
    employee.setGender("M");

    EmployeeBo employeeBo = employeeMapper.entityToBo(employee);
    assertEquals(employeeBo.getName(), employee.getName());
    assertEquals(employeeBo.getGender(), employee.getGender());
    assertEquals(employeeBo.getDob(), employee.getDob());
  }

  @Test
  void testBoToEntity() {
    EmployeeBo employeeBo = new EmployeeBo();
    employeeBo.setName("Sayan");
    employeeBo.setDob(Date.valueOf("2000-08-11"));
    employeeBo.setGender("M");

    Employee employee = employeeMapper.boToEntity(employeeBo);
    assertEquals(employee.getName(), employeeBo.getName());
    assertEquals(employee.getGender(), employeeBo.getGender());
    assertEquals(employee.getDob(), employeeBo.getDob());
  }

  @Test
  void testCreateRequestToBo() {
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    createEmployeeRequest.setName("Sayan");
    createEmployeeRequest.setDob(Date.valueOf("2000-08-11"));
    createEmployeeRequest.setGender("M");
    EmployeeBo employeeBo =
        employeeMapper.createRequestToBo(createEmployeeRequest, IdGenerator.generateEmployeeId());
    System.out.println(employeeBo.getEmpId());
    assertEquals("Sayan", employeeBo.getName());
    assertEquals(Date.valueOf("2000-08-11"), employeeBo.getDob());
    assertEquals("M", employeeBo.getGender());
  }
}
