package com.lessons.controller;

import com.lessons.dto.EmployeeDto;
import com.lessons.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<EmployeeDto> employees = employeeService.getAll();
        if(employees == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        EmployeeDto employee = employeeService.getById(id);
        if(employee == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }

    @PostMapping("employee")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto newEmployee){
        if(newEmployee == null)
            return ResponseEntity.badRequest().body(null);
        EmployeeDto employee = employeeService.create(newEmployee);
        return ResponseEntity.created(URI.create("/employee/" + employee.getId()))
                .body(employee);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id, @RequestBody EmployeeDto newEmployee){
        if(id == null || newEmployee == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(employeeService.update(newEmployee, id));
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> delete(@PathVariable Integer id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
