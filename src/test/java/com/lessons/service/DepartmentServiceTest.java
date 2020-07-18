package com.lessons.service;

import com.lessons.dao.DepartmentDao;
import com.lessons.entity.Department;
import com.lessons.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:app-context.xml"})
public class DepartmentServiceTest {

    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void getAll(){
        Department department = new Department();
        List<Department> info = new ArrayList<>();
        info.add(department);

        when(departmentDao.findAll()).thenReturn(info);

        List<Department> departments = departmentService.getAll();

        assertNotNull(departments);
        assertEquals(1, departments.size());
    }

    @Test
    public void getById(){
        Department department = new Department();
        department.setName("depGetById");

        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(department));

        Department department1 = departmentService.getById(1);

        assertEquals("depGetById", department1.getName());
    }

    @Test
    public void create(){
        Department department = new Department();
        department.setName("depCreate");

        when(departmentDao.save(any(Department.class))).thenReturn(department);

        Department department1 = departmentService.create(department);

        assertEquals(department, department1);
    }

    @Test
    public void update(){
        Department department1 = new Department();
        department1.setName("dep1");
        department1.setId(1);

        Department department2 = new Department();
        department2.setName("dep2");

        when(departmentDao.findById(1)).thenReturn(Optional.of(department1));
        when(departmentDao.save(any(Department.class))).thenReturn(department2);

        Department department3 = departmentService.update(department2, 1);

        assertEquals(department2.getName(), department3.getName());
    }
}
