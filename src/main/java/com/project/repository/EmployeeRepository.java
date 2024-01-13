package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Employee;
import com.project.response.ApiResponse;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String>{
    Employee findByEmail(String email);
    ApiResponse deleteByEmail(String email);
}