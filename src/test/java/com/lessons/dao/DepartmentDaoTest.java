package com.lessons.dao;

import com.lessons.entity.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:app-context.xml"})
public class DepartmentDaoTest {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentDaoTest(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Test
    public void getAll(){
        List<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
        assertEquals(0, departments.size());
    }

    @Test
    public void save(){
        Department department = new Department();
        department.setName("dep1");
        Department department1 = departmentDao.save(department);
        assertNotNull(department1);
    }


    @Test
    public void getById(){
        Department department = new Department();
        department.setName("dep1");
        departmentDao.save(department);
        Department department1 = departmentDao.findById(1).orElseGet(null);
        assertEquals("dep1", department1.getName());
    }
    
    @Test
    public void delete(){
        Department department = new Department();
        department.setName("dep1");
        departmentDao.save(department);

        departmentDao.deleteById(1);

        List<Department> departments = departmentDao.findAll();

        assertEquals(0, departments.size());
    }

}
