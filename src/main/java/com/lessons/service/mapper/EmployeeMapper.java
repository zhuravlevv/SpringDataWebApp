package com.lessons.service.mapper;

import com.lessons.dao.DepartmentDao;
import com.lessons.dto.EmployeeDto;
import com.lessons.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements EntityMapper<Employee, EmployeeDto> {

    private final DepartmentDao departmentDao;

    @Autowired
    public EmployeeMapper(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public EmployeeDto toDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        if(employee.getDepartment() != null)
            employeeDto.setDepartmentId(employee.getDepartment().getId());
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }

    public Employee fromDto(EmployeeDto employeeDto){
        Employee employee = new Employee();
        try {
            employee.setDepartment(departmentDao
                    .findById(employeeDto.getDepartmentId())
                    .orElseThrow(Exception::new));
        }catch (Exception e){
            System.out.println("Department doesn't exist");
        }
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setId(employeeDto.getId());
        return employee;
    }
}
