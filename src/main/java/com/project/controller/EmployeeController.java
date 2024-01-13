package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UpdateDto;
import com.project.dto.UpdateInfoDto;
import com.project.entities.Employee;
import com.project.response.ApiResponse;
import com.project.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    //save employee api
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> createEmp(@RequestBody Employee emp){
        if(emp.getName().length()<3){
            ApiResponse response = new ApiResponse("Employee name should be atleast of length 3", HttpStatus.BAD_REQUEST, false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST); 
        }
        if(emp.getEmail()==""){
            ApiResponse response = new ApiResponse("Employee should have an email", HttpStatus.BAD_REQUEST, false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST); 
        } 
        Employee emp1 = employeeService.saveEmployee(emp);
        if(emp1!=null){
            ApiResponse response = new ApiResponse("Employee saved successfullly", HttpStatus.CREATED, true);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            ApiResponse response = new ApiResponse("Employee with this email already exists!!", HttpStatus.BAD_REQUEST, false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    // get all employee api
   @GetMapping("/getAll")
   public ResponseEntity<List<Employee>> getAllEmployees(){
      List<Employee> list = employeeService.allEmployees();
      if(list.size()!=0) return new ResponseEntity<>(list,HttpStatus.OK);
      else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
   }

    // get employee by email api
   @GetMapping("/getByEmail/{email}")
   public ResponseEntity<Employee> getByEmail(@PathVariable String email){
        Employee emp1 = employeeService.getEmployeeByEmail(email);
        if(emp1!=null){
            return new ResponseEntity<>(emp1,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
   }

   // update email
   @PutMapping("/updateEmail")
   public ResponseEntity<ApiResponse> updateEmail(@RequestBody UpdateDto updateDto){
       Employee emp1 = employeeService.updatEmployeeEmail(updateDto);
       if(emp1!=null){
         ApiResponse response = new ApiResponse("Employee's email updates successfullyt",HttpStatus.OK,true);
         return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
       }
       else{
         ApiResponse response = new ApiResponse("Check current and new email",HttpStatus.BAD_REQUEST,false);
         return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
       }
   }

   //update by email
   @PutMapping("/updateEmployee")
   public ResponseEntity<ApiResponse> updateEmployee(@RequestBody UpdateInfoDto updateInfoDto){
       Employee emp1 = employeeService.updateEmployee(updateInfoDto);
       if(emp1!=null){
        ApiResponse response = new ApiResponse("Employee updated successfull",HttpStatus.OK,true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
       }
       else{
        ApiResponse response = new ApiResponse("Employee with given "+updateInfoDto.getEmail()+" doesn't exists",HttpStatus.BAD_REQUEST,true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
       }
   }

   // delete employee by email  api
   @DeleteMapping("/delete/{email}")
   public ResponseEntity<ApiResponse> deleteByEmail(@PathVariable String email){
      String isDeleted = employeeService.deleteEmployeeByEmail(email);
      if(isDeleted!=null){
        ApiResponse response = new ApiResponse("Employee with email "+email+" deleted successfully",HttpStatus.OK,true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
      }
      else{
        ApiResponse response = new ApiResponse("Employee with email "+email+" doesn't exists",HttpStatus.BAD_REQUEST,false);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
      }
   }

   //    delete all employee api
   @DeleteMapping("/deleteAll")
   public ResponseEntity<ApiResponse> deleteAll(){
         Boolean msg = employeeService.deleteAll();
         ApiResponse response;
         if(msg) {
              response = new ApiResponse("Successfully deleted all records",HttpStatus.OK,true);
         }
         else{
              response = new ApiResponse("There is some issue in deleting all records",HttpStatus.INTERNAL_SERVER_ERROR,     false);
         }
         return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
     }
    
}
