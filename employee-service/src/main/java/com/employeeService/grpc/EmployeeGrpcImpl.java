package com.employeeService.grpc;

import com.google.protobuf.Empty;
import com.mysql_crud.grpc.CreateEmployeeRequest;
import com.mysql_crud.grpc.CreateEmployeeResponse;
import com.mysql_crud.grpc.DeleteEmployeeRequest;
import com.mysql_crud.grpc.Employee;
import com.mysql_crud.grpc.EmployeesServiceGrpc;
import com.mysql_crud.grpc.GetEmployeeRequest;
import com.mysql_crud.grpc.GetEmployeeResponse;
import com.mysql_crud.grpc.ListEmployeesRequest;
import com.mysql_crud.grpc.ListEmployeesResponse;
import com.mysql_crud.grpc.UpdateEmployeeRequest;
import com.mysql_crud.grpc.UpdateEmployeeResponse;
import org.employee.bo.EmployeeBo;
import org.employee.mapper.GrpcMapper;
import com.employeeService.service.EmployeeService;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class EmployeeGrpcImpl extends EmployeesServiceGrpc.EmployeesServiceImplBase {
  @Autowired
  EmployeeService empService;

  @Override
  public void createEmployee(final CreateEmployeeRequest request,
                             final StreamObserver<CreateEmployeeResponse> responseObserver) {
    final EmployeeBo employeeBo = empService.createEmployee(GrpcMapper.employeeRequest(request));
    final CreateEmployeeResponse createEmployeeResponse = CreateEmployeeResponse.newBuilder()
        .setEmployee(GrpcMapper.boToProto(employeeBo))
        .build();
    responseObserver.onNext(createEmployeeResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getEmployee(final GetEmployeeRequest request,
                          final StreamObserver<GetEmployeeResponse> responseObserver) {
    final EmployeeBo employeeBo = empService.getEmployee(request.getId());
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(employeeBo.getDob());
    final GetEmployeeResponse employeeResponse = GetEmployeeResponse.newBuilder()
        .setEmployee(GrpcMapper.boToProto(employeeBo))
        .build();
    responseObserver.onNext(employeeResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void listEmployees(final ListEmployeesRequest request,
                            final StreamObserver<ListEmployeesResponse> responseObserver) {
    final int pageNumber = (request.getPageNumber() == 0) ? 0 : request.getPageNumber();
    final int pageSize = (request.getPageSize() == 0) ? 10 : request.getPageSize();
    final List<EmployeeBo> employeeBoList =
        empService.listEmployees(pageSize, pageNumber);
    final List<Employee> employees = new ArrayList<>();
    for (final EmployeeBo employeeBo : employeeBoList) {
      employees.add(GrpcMapper.boToProto(employeeBo));
    }
    final ListEmployeesResponse listEmployeesResponse = ListEmployeesResponse.newBuilder()
        .addAllEmployees(employees)
        .build();
    responseObserver.onNext(listEmployeesResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void updateEmployee(final UpdateEmployeeRequest request,
                             final StreamObserver<UpdateEmployeeResponse> responseObserver) {
    final EmployeeBo employeeBo = empService.updateEmployee(request.getEmployee().getId(),
        GrpcMapper.updateEmployeeRequest(request));
    final UpdateEmployeeResponse updateEmployeeResponse = UpdateEmployeeResponse.newBuilder()
        .setEmployee(GrpcMapper.boToProto(employeeBo))
        .build();
    responseObserver.onNext(updateEmployeeResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void deleteEmployee(final DeleteEmployeeRequest request,
                             final StreamObserver<Empty> responseObserver) {
    empService.deleteEmployee(request.getId());
    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }

}







