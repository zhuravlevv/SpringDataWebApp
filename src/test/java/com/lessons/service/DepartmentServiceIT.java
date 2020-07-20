package com.lessons.service;

import com.lessons.dto.DepartmentDto;
import com.lessons.entity.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "file:src/main/webapp/WEB-INF/app-context.xml"})
public class DepartmentServiceIT {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentServiceIT(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Test
    public void update(){
        DepartmentDto department1 = new DepartmentDto();
        department1.setName("dep1");
        DepartmentDto returnedDepartment = departmentService.create(department1);

        DepartmentDto department2 = new DepartmentDto();
        department2.setName("dep2");

        departmentService.update(department2, returnedDepartment.getId());
        DepartmentDto department = departmentService.getById(returnedDepartment.getId());

        assertEquals("dep2", department.getName());
    }

    @Test
    public void delete(){
        DepartmentDto department1 = new DepartmentDto();
        department1.setName("dep1");
        DepartmentDto returnedDepartment = departmentService.create(department1);

        departmentService.delete(returnedDepartment.getId());

        DepartmentDto department2 = departmentService.getById(returnedDepartment.getId());

        assertNull(department2);
    }
}
