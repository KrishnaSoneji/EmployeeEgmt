package com.project.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.UpdateDto;
import com.project.dto.UpdateInfoDto;
import com.project.entities.Address;
import com.project.entities.Employee;
import com.project.repository.AddressRepository;
import com.project.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    // save 
    @Override
    public Employee saveEmployee(Employee emp) {
        Employee employee = employeeRepository.findByEmail(emp.getEmail());
        if(employee==null){
            String id = UUID.randomUUID().toString();
            emp.setId(id);
            return employeeRepository.save(emp);
        }
        else{
            return null;
        }
    }

    // read all
    @Override
    public List<Employee> allEmployees() {
        return employeeRepository.findAll();
    }

    // read by email
    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    //update employee info
    @Override
    public Employee updateEmployee(UpdateInfoDto updateInfoDto) {
        Employee emp1 = employeeRepository.findByEmail(updateInfoDto.getEmail());
        // Address address = Add
        if(emp1!=null){
            emp1.setName(updateInfoDto.getName());
            Address address = emp1.getAddress();
            Address updatedAddress = updateInfoDto.getAddress();
            address.setCity(updatedAddress.getCity());
            address.setStreet(updatedAddress.getStreet());
            address.setHouseName(updatedAddress.getHouseName());
            address.setHouseNo(updatedAddress.getHouseNo());
            emp1.setAddress(address);
            employeeRepository.save(emp1);
            return emp1;
        }
        else{
           return null;
        }
    }

    // update emp email
    @Override
    public Employee updatEmployeeEmail(UpdateDto updateDto) {
        Employee emp1 = employeeRepository.findByEmail(updateDto.getPrevEmail());
        System.out.println(emp1);
        
        Employee emp2 = employeeRepository.findByEmail(updateDto.getNewEmail());
        System.out.println(emp2);
        // employee should be present with the given prevemail and  employee with given new email should not be present
        if(emp1!=null && emp2==null){
            emp1.setEmail(updateDto.getNewEmail());
            employeeRepository.save(emp1);
            return emp1;
        }
        else{
           return null;
        }
    }

    // delete by email
    @Override
    public String deleteEmployeeByEmail(String email) {
        Employee emp = employeeRepository.findByEmail(email);
        if(emp!=null){
          employeeRepository.delete(emp);
          return "deleted";
        }
        else{
            return null;
        }
    }

    // delete all
    @Override
    public Boolean deleteAll(){
        employeeRepository.deleteAll();
        return true;
    }
    
}
