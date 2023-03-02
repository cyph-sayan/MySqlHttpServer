package org.employee.mapper;

import org.employee.bo.EmployeeBo;
import org.employee.entity.Employee;
import org.employee.model.CreateEmployeeRequest;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  List<EmployeeBo> listEntityToListBo(List<Employee> employees);

  EmployeeBo entityToBo(Employee employee);

  Employee boToEntity(EmployeeBo employeeBo);

  @Mapping(target = "empId", source = "id")
  EmployeeBo createRequestToBo(CreateEmployeeRequest employeeRequest, String id);
}
