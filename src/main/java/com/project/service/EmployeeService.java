package com.project.service;

import java.util.List;

import com.project.dto.UpdateDto;
import com.project.dto.UpdateInfoDto;
import com.project.entities.Employee;

public interface EmployeeService {
    
    // save employee
    Employee saveEmployee(Employee emp);

    // read all employee
   List<Employee> allEmployees();
   
    // read employee by email
   Employee getEmployeeByEmail(String email);

    // update employee
   Employee updateEmployee(UpdateInfoDto updateInfoDto);

   //update email of employee
   Employee updatEmployeeEmail(UpdateDto updateDto);

    // delete employee by email
    String deleteEmployeeByEmail(String email);

    // delete all
    Boolean deleteAll();
}
