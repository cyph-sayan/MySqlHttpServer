package org.employee.repository;

import org.employee.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Employee findByempId(String id);

  @Transactional
  void deleteByempId(String id);

  @Transactional
  @Modifying
  @Query(value = "update employee e set e.hobby_created = ?1 where e.emp_id = ?2",nativeQuery = true)
  void setHobbyStatus(int status, String emp_id);
}
