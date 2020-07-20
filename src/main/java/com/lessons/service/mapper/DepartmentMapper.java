package com.lessons.service.mapper;

import com.lessons.dao.EmployeeDao;
import com.lessons.dto.DepartmentDto;
import com.lessons.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper implements EntityMapper<Department, DepartmentDto>{

    private final EmployeeDao employeeDao;

    @Autowired
    public DepartmentMapper(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public DepartmentDto toDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        List<BigDecimal> salaries = new ArrayList<>();
        if(department.getEmployees() != null)
            department.getEmployees().forEach(x -> salaries.add(x.getSalary()));
        BigDecimal averageSalary = new BigDecimal("0");
        for (BigDecimal salary:
             salaries) {
            averageSalary = averageSalary.add(salary);
        }
        departmentDto.setAverageSalary(averageSalary);
        return departmentDto;
    }

    public Department fromDto(DepartmentDto departmentDto){
        Department department = new Department();
        department.setName(departmentDto.getName());
        return department;
    }
}
