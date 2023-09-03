package com.Emp.demo.controller;


import com.Emp.demo.exception.ResourceNotFoundException;
import com.Emp.demo.model.Employee;
import com.Emp.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/by-name/{name}")
    public List<Employee> getEmpByFirstName(@PathVariable String name) {
        return employeeRepository.findByFirstName(name);
    }

    @GetMapping("/designation/{designation}")
    public List<Employee> getEmpByDesignation(@PathVariable String designation) {
        return employeeRepository.findByDesignation(designation);
    }

    @GetMapping("/dept/{department}")
    public List<Employee> getEmpByDept(@PathVariable String department) {
        return employeeRepository.findByDepartment(department);
    }

    @GetMapping("/month/{months}")
    public List<Employee> getEmpByHiredWithinMonth(@PathVariable int months) {
        return employeeRepository.findEmployeesHiredWithinDefinedMonths(months);
    }

    @GetMapping("/salary-gt/{salary}")
    public List<Employee> getEmpByFirstName(@PathVariable Double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }
}