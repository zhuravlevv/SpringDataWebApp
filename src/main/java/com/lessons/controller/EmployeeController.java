package com.lessons.controller;

import com.lessons.entity.Department;
import com.lessons.entity.Employee;
import com.lessons.service.EmployeeService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.REException;

import javax.xml.ws.Service;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAll(){
        List<Employee> employees = employeeService.getAll();
        if(employees == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        Employee employee = employeeService.getById(id);
        if(employee == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee newEmployee){
        if(newEmployee == null)
            return ResponseEntity.badRequest().body(null);
        Employee employee = employeeService.create(newEmployee);
        return ResponseEntity.created(URI.create("/employee/" + employee.getId()))
                .body(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee newEmployee){
        if(id == null || newEmployee == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(employeeService.update(newEmployee, id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Integer id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
