package com.lessons.service.impl;

import com.lessons.dao.EmployeeDao;
import com.lessons.entity.Employee;
import com.lessons.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        try{
            return employeeDao.findById(id).orElseThrow(Exception::new);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee newEmployee, Integer id) {
        Employee employee = getById(id);
        employee.setDepartment(newEmployee.getDepartment());
        employee.setSalary(newEmployee.getSalary());
        employee.setName(newEmployee.getName());
        return employee;
    }

    @Override
    public void delete(Integer id) {
        employeeDao.deleteById(id);
    }
}
