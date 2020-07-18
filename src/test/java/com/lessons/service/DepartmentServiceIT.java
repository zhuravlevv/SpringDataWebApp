package com.lessons.service;

import com.lessons.entity.Department;
import com.lessons.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:app-context.xml"})
public class DepartmentServiceIT {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentServiceIT(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Test
    public void update(){
        Department department1 = new Department();
        department1.setName("dep1");
        Department returnedDepartment = departmentService.create(department1);

        Department department2 = new Department();
        department2.setName("dep2");

        departmentService.update(department2, returnedDepartment.getId());
        Department department = departmentService.getById(returnedDepartment.getId());

        assertEquals("dep2", department.getName());
    }

    @Test
    public void delete(){
        Department department1 = new Department();
        department1.setName("dep1");
        Department returnedDepartment = departmentService.create(department1);

        departmentService.delete(returnedDepartment.getId());

        Department department2 = departmentService.getById(returnedDepartment.getId());

        assertNull(department2);
    }
}
