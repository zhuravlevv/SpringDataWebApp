package com.lessons.service.impl;

import com.lessons.dao.DepartmentDao;
import com.lessons.entity.Department;
import com.lessons.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() {
        return departmentDao.findAll();
    }

    public Department getById(Integer id){
        try {
            return departmentDao.findById(id).orElseThrow(Exception::new);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Department create(Department department) {
        return departmentDao.save(department);
    }

    public Department update(Department newDepartment, Integer id) {
        Department department = getById(id);
        department.setName(newDepartment.getName());
        newDepartment.getEmployees().forEach(department::addEmployee);
        return department;
    }

    public void delete(Integer id) {
        departmentDao.deleteById(id);
    }
}
