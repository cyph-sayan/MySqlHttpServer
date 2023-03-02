package com.employeeAsync.service;

import com.employeeAsync.dao.EmployeeHobbyDao;
import org.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  @Autowired
  EmployeeHobbyDao employeeHobbyDao;

  @KafkaListener(topics = "emp-hobby",groupId = "hobbies",containerFactory = "kafkaListener")
  public void consume(String response){
    System.out.println("Hobby Entry Created Successfully For Employee:");
    employeeHobbyDao.setEmployeeHobby(1,response);
  }
}
