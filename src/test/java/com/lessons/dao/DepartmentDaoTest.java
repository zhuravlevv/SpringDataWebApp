package com.lessons.dao;

import com.lessons.entity.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(departments.size() == 0);
    }

}
