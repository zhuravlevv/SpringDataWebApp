package com.lessons.dao;

import com.lessons.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml","file:src/main/webapp/WEB-INF/app-context.xml"})
public class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeDaoTest(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Test
    public void getAll(){
        List<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertEquals(0, employees.size());
    }

    @Test
    public void save(){
        Employee employee = new Employee();
        Employee employee1 = employeeDao.save(employee);
        assertNotNull(employee1.getId());
    }

    @Test
    public void getById(){
        Employee employee = new Employee();
        Employee employee1 = employeeDao.save(employee);

        Employee employee2 = employeeDao.findById(employee1.getId()).orElseGet(null);

        assertEquals(employee2, employee1);
    }

    @Test
    public void delete(){
        Employee employee = new Employee();
        Employee employee1 = employeeDao.save(employee);

        employeeDao.deleteById(employee1.getId());

        List<Employee> employees = employeeDao.findAll();

        assertEquals(0, employees.size());
    }
}
