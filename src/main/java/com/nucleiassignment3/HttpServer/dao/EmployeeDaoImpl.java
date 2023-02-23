package com.nucleiassignment3.HttpServer.dao;

import com.nucleiassignment3.HttpServer.bo.EmployeeBo;
import com.nucleiassignment3.HttpServer.entity.Employee;
import com.nucleiassignment3.HttpServer.mapper.EmployeeMapper;
import com.nucleiassignment3.HttpServer.model.UpdateEmployeeRequest;
import com.nucleiassignment3.HttpServer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeBo createEmployee(EmployeeBo employeeBo)
    {

        return employeeMapper.entityToBo(employeeRepository.save(employeeMapper.boToEntity(employeeBo)));
    }

    @Cacheable(value = "employees", key="#id")
    @Override
    public EmployeeBo getEmployee(String id)
    {
        return employeeMapper.entityToBo(employeeRepository.findByempId(id));
    }

    @Override
    public List<EmployeeBo> listEmployees(int pageSize, int pageNumber)
    {
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        List<Employee> employees=employeeRepository.findAll(pageable).getContent();
        return employeeMapper.listEntityToListBo(employees);
    }

    @Override
    public EmployeeBo updateEmployee(String  id, UpdateEmployeeRequest updateEmployeeRequest)
    {
          Optional<Employee> optionalEmployee=employeeRepository.findById(id);
          Employee employee=optionalEmployee.get();
          employee=employeeMapper.updateRequestToEntity(updateEmployeeRequest);
          return employeeMapper.entityToBo(employeeRepository.save(employee));
    }

    @CacheEvict(value = "users", allEntries=true)
    @Override
    public void deleteEmployee(String id)
    {
        employeeRepository.deleteByempId(id);
    }
}
