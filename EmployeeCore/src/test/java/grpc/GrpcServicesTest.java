package grpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mysql_crud.grpc.CreateEmployeeRequest;
import com.mysql_crud.grpc.CreateEmployeeResponse;
import com.mysql_crud.grpc.Date;
import com.mysql_crud.grpc.DeleteEmployeeRequest;
import com.mysql_crud.grpc.Employee;
import com.mysql_crud.grpc.EmployeesServiceGrpc;
import com.mysql_crud.grpc.GetEmployeeRequest;
import com.mysql_crud.grpc.GetEmployeeResponse;
import com.mysql_crud.grpc.ListEmployeesRequest;
import com.mysql_crud.grpc.ListEmployeesResponse;
import com.mysql_crud.grpc.UpdateEmployeeRequest;
import com.mysql_crud.grpc.UpdateEmployeeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class GrpcServicesTest {

  @Test
  void createEmployeeTest() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    EmployeesServiceGrpc.EmployeesServiceBlockingStub stub =
        EmployeesServiceGrpc.newBlockingStub(channel);
    CreateEmployeeResponse employeeResponse = stub.createEmployee(CreateEmployeeRequest.newBuilder()
        .setEmployee(Employee.newBuilder()
            .setName("Rahul")
            .setGender("M")
            .setDob(Date.newBuilder().setDay(21).setMonth(12).setYear(2007).build())
            .build())
        .build());
    assertEquals("Rahul", employeeResponse.getEmployee().getName());
    assertEquals("M", employeeResponse.getEmployee().getGender());
    assertEquals(21, employeeResponse.getEmployee().getDob().getDay());
    assertEquals(12, employeeResponse.getEmployee().getDob().getMonth());
    assertEquals(2007, employeeResponse.getEmployee().getDob().getYear());
    channel.shutdown();
  }

  @Ignore
  @Test
  void getEmployeeTest() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    EmployeesServiceGrpc.EmployeesServiceBlockingStub stub =
        EmployeesServiceGrpc.newBlockingStub(channel);
    GetEmployeeResponse getEmployeeResponse = stub.getEmployee(GetEmployeeRequest
        .newBuilder()
        .setId("GN-ZVC95SR2KDNB70WVALZRZ")
        .build());
    assertEquals("GN-ZVC95SR2KDNB70WVALZRZ", getEmployeeResponse.getEmployee().getId());
    assertEquals("Pradahan", getEmployeeResponse.getEmployee().getName());
    assertEquals("M", getEmployeeResponse.getEmployee().getGender());
    assertEquals(11, getEmployeeResponse.getEmployee().getDob().getDay());
    assertEquals(6, getEmployeeResponse.getEmployee().getDob().getMonth());
    assertEquals(2000, getEmployeeResponse.getEmployee().getDob().getYear());
    channel.shutdown();
  }

  @Ignore
  @Test
  void updateEmployee() {
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    EmployeesServiceGrpc.EmployeesServiceBlockingStub stub =
        EmployeesServiceGrpc.newBlockingStub(managedChannel);
    UpdateEmployeeResponse updateEmployeeResponse =
        stub.updateEmployee(UpdateEmployeeRequest.newBuilder()
            .setEmployee(
                Employee.newBuilder().setId("GN-ZVC95SR2KDNB70WVALZRZ").setName("RamPrasad")
                    .setDob(Date.newBuilder().setDay(11).setMonth(8).setYear(2000).build())
                    .setGender("M").build())
            .build());
    assertEquals("GN-ZVC95SR2KDNB70WVALZRZ", updateEmployeeResponse.getEmployee().getId());
    assertEquals("RamPrasad", updateEmployeeResponse.getEmployee().getName());
    assertEquals("M", updateEmployeeResponse.getEmployee().getGender());
    assertEquals(11, updateEmployeeResponse.getEmployee().getDob().getDay());
    assertEquals(8, updateEmployeeResponse.getEmployee().getDob().getMonth());
    assertEquals(2000, updateEmployeeResponse.getEmployee().getDob().getYear());
    managedChannel.shutdown();
  }

  @Ignore
  @Test
  void listEmployees() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    EmployeesServiceGrpc.EmployeesServiceBlockingStub stub =
        EmployeesServiceGrpc.newBlockingStub(channel);
    ListEmployeesResponse listEmployeesResponse = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .build());
    assertEquals(1, listEmployeesResponse.getEmployeesList().size());
    channel.shutdown();
  }

  @Test
  void endToEndTest() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();
    EmployeesServiceGrpc.EmployeesServiceBlockingStub stub =
        EmployeesServiceGrpc.newBlockingStub(channel);
    CreateEmployeeResponse employeeResponse1 =
        stub.createEmployee(CreateEmployeeRequest.newBuilder()
            .setEmployee(Employee.newBuilder()
                .setName("Aravind")
                .setGender("M")
                .setDob(Date.newBuilder().setDay(21).setMonth(12).setYear(1998).build())
                .build())
            .build());
    assertEquals("Aravind", employeeResponse1.getEmployee().getName());
    assertEquals("M", employeeResponse1.getEmployee().getGender());
    assertEquals(21, employeeResponse1.getEmployee().getDob().getDay());
    assertEquals(12, employeeResponse1.getEmployee().getDob().getMonth());
    assertEquals(1998, employeeResponse1.getEmployee().getDob().getYear());
    List<String> employeeId = new ArrayList<>();
    employeeId.add(employeeResponse1.getEmployee().getId());
    CreateEmployeeResponse employeeResponse2 =
        stub.createEmployee(CreateEmployeeRequest.newBuilder()
            .setEmployee(Employee.newBuilder()
                .setName("Shalu")
                .setGender("F")
                .setDob(Date.newBuilder().setDay(11).setMonth(2).setYear(1995).build())
                .build())
            .build());
    assertEquals("Shalu", employeeResponse2.getEmployee().getName());
    assertEquals("F", employeeResponse2.getEmployee().getGender());
    assertEquals(11, employeeResponse2.getEmployee().getDob().getDay());
    assertEquals(2, employeeResponse2.getEmployee().getDob().getMonth());
    assertEquals(1995, employeeResponse2.getEmployee().getDob().getYear());
    employeeId.add(employeeResponse1.getEmployee().getId());
    ListEmployeesResponse listEmployeesResponse1 = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .setPageNumber(0)
        .setPageSize(10)
        .build());
    assertEquals(2, listEmployeesResponse1.getEmployeesList().size());
    GetEmployeeResponse getEmployeeResponse1 = stub.getEmployee(GetEmployeeRequest
        .newBuilder()
        .setId(employeeId.get(0))
        .build());
    assertEquals(employeeId.get(0), getEmployeeResponse1.getEmployee().getId());
    assertEquals("Aravind", getEmployeeResponse1.getEmployee().getName());
    assertEquals("M", getEmployeeResponse1.getEmployee().getGender());
    assertEquals(21, getEmployeeResponse1.getEmployee().getDob().getDay());
    assertEquals(12, getEmployeeResponse1.getEmployee().getDob().getMonth());
    assertEquals(1998, getEmployeeResponse1.getEmployee().getDob().getYear());
    UpdateEmployeeResponse updateEmployeeResponse =
        stub.updateEmployee(UpdateEmployeeRequest.newBuilder()
            .setEmployee(
                Employee.newBuilder().setId(employeeId.get(1)).setName("RamPrasad")
                    .setDob(Date.newBuilder().setDay(11).setMonth(8).setYear(2000).build())
                    .setGender("M").build())
            .build());
    assertEquals(employeeId.get(1), updateEmployeeResponse.getEmployee().getId());
    assertEquals("RamPrasad", updateEmployeeResponse.getEmployee().getName());
    assertEquals("M", updateEmployeeResponse.getEmployee().getGender());
    assertEquals(11, updateEmployeeResponse.getEmployee().getDob().getDay());
    assertEquals(8, updateEmployeeResponse.getEmployee().getDob().getMonth());
    assertEquals(2000, updateEmployeeResponse.getEmployee().getDob().getYear());
    GetEmployeeResponse getEmployeeResponse2 = stub.getEmployee(GetEmployeeRequest
        .newBuilder()
        .setId(employeeId.get(1))
        .build());
    assertEquals(employeeId.get(1), getEmployeeResponse2.getEmployee().getId());
    assertEquals("RamPrasad", getEmployeeResponse2.getEmployee().getName());
    assertEquals("M", getEmployeeResponse2.getEmployee().getGender());
    assertEquals(11, getEmployeeResponse2.getEmployee().getDob().getDay());
    assertEquals(8, getEmployeeResponse2.getEmployee().getDob().getMonth());
    assertEquals(2000, getEmployeeResponse2.getEmployee().getDob().getYear());
    stub.deleteEmployee(DeleteEmployeeRequest
        .newBuilder()
        .setId(employeeId.get(1))
        .build());
    employeeId.remove(1);
    ListEmployeesResponse listEmployeesResponse2 = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .setPageNumber(0)
        .setPageSize(10)
        .build());
    assertEquals(1, listEmployeesResponse2.getEmployeesList().size());
    CreateEmployeeResponse employeeResponse3 =
        stub.createEmployee(CreateEmployeeRequest.newBuilder()
            .setEmployee(Employee.newBuilder()
                .setName("Mukti")
                .setGender("F")
                .setDob(Date.newBuilder().setDay(1).setMonth(1).setYear(2000).build())
                .build())
            .build());
    assertEquals("Mukti", employeeResponse3.getEmployee().getName());
    assertEquals("F", employeeResponse3.getEmployee().getGender());
    assertEquals(1, employeeResponse3.getEmployee().getDob().getDay());
    assertEquals(1, employeeResponse3.getEmployee().getDob().getMonth());
    assertEquals(2000, employeeResponse3.getEmployee().getDob().getYear());
    employeeId.add(employeeResponse3.getEmployee().getId());
    ListEmployeesResponse listEmployeesResponse3 = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .build());
    assertEquals(2, listEmployeesResponse3.getEmployeesList().size());
    ListEmployeesResponse listEmployeesResponse4 = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .setPageNumber(0)
        .build());
    assertEquals(2, listEmployeesResponse4.getEmployeesList().size());
    ListEmployeesResponse listEmployeesResponse5 = stub.listEmployees(ListEmployeesRequest
        .newBuilder()
        .setPageSize(5)
        .build());
    assertEquals(2, listEmployeesResponse5.getEmployeesList().size());
  }
}
