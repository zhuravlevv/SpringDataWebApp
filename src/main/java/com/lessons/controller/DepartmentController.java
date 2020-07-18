package com.lessons.controller;

import com.lessons.entity.Department;
import com.lessons.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAll(){
        List<Department> departments = departmentService.getAll();
        if(departments == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        Department department = departmentService.getById(id);
        if(department == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(department);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> create(@RequestBody Department newDepartment){
        Department department = departmentService.create(newDepartment);
        if(department == null){
            return ResponseEntity.badRequest()
                    .body(null);
        }
        return ResponseEntity.created(URI.create("deaprtment/" + department.getId()))
                .body(department);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> update(@PathVariable Integer id, @RequestBody Department newDepartment){
        if(id == null || newDepartment == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(departmentService.update(newDepartment, id));
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Department> delete(@PathVariable Integer id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
