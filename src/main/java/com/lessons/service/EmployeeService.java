package com.lessons.service;

import com.lessons.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAll();

    public Employee getById(Integer id);

    public Employee create(Employee employee);

    public Employee update(Employee newEmployee, Integer id);

    public void delete(Integer id);
}
