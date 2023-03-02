package org.employee.mapper;

import com.mysql_crud.grpc.CreateEmployeeRequest;
import com.mysql_crud.grpc.Date;
import com.mysql_crud.grpc.Employee;
import org.employee.bo.EmployeeBo;
import org.employee.model.UpdateEmployeeRequest;
import java.util.Calendar;
import org.jetbrains.annotations.NotNull;

/**
 * Maps Proto Objects To Repository Entities, Request Models and Vice-Versa.
 */
public final class GrpcMapper {
  private GrpcMapper() {

  }

  /**
   * Maps EmployeeBO to proto object.
   */
  public static Employee boToProto(@NotNull final EmployeeBo employeeBo) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(employeeBo.getDob());
    return Employee.newBuilder()
        .setName(employeeBo.getName())
        .setGender(employeeBo.getGender())
        .setId(employeeBo.getEmpId())
        .setDob(Date.newBuilder()
            .setDay(calendar.get(calendar.DAY_OF_MONTH))
            .setMonth(calendar.get(calendar.MONTH) + 1)
            .setYear(calendar.get(calendar.YEAR))
            .build())
        .build();
  }

  /**
   * Maps Proto Create Employee Request to Model Create Employee Request.
   */
  public static org.employee.model.CreateEmployeeRequest employeeRequest(
      final CreateEmployeeRequest request) {
    final org.employee.model.CreateEmployeeRequest createEmployeeRequest =
        new org.employee.model.CreateEmployeeRequest();
    createEmployeeRequest.setName(request.getEmployee().getName());
    final Date dob = request.getEmployee().getDob();
    createEmployeeRequest.setDob(java.sql.Date.valueOf(
        (dob.getYear()) + "-" + (dob.getMonth()) + "-"
            + (dob.getDay())));
    createEmployeeRequest.setGender(request.getEmployee().getGender());
    return createEmployeeRequest;
  }

  /**
   * Maps Proto Update Employee Request to Model Update Employee Request.
   */
  public static UpdateEmployeeRequest updateEmployeeRequest(
      final com.mysql_crud.grpc.UpdateEmployeeRequest updateEmployeeRequest) {
    final UpdateEmployeeRequest request = new UpdateEmployeeRequest();
    request.setName(updateEmployeeRequest.getEmployee().getName());
    request.setGender(updateEmployeeRequest.getEmployee().getGender());
    final Date dob = updateEmployeeRequest.getEmployee().getDob();
    request.setDob((java.sql.Date.valueOf(
        (dob.getYear()) + "-" + (dob.getMonth()) + "-"
            + (dob.getDay()))));
    return request;
  }
}
