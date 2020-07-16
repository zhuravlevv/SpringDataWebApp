package com.lessons.service;

import com.lessons.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAll();

    public Department getById(Integer id) throws Exception;

    public Department create(Department department);

    public Department update(Department newDepartment, Integer id);

    public void delete(Integer id);
}
