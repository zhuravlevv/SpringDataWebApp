package com.lessons.service.impl;

import com.lessons.dao.DepartmentDao;
import com.lessons.dao.EmployeeDao;
import com.lessons.dto.EmployeeDto;
import com.lessons.entity.Department;
import com.lessons.entity.Employee;
import com.lessons.service.EmployeeService;
import com.lessons.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    private final EmployeeMapper employeeMapper;

    private final DepartmentDao departmentDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeMapper employeeMapper, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.employeeMapper = employeeMapper;
        this.departmentDao = departmentDao;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeDao.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employees.forEach(x->{
            employeeDtos.add(employeeMapper.toDto(x));
        });
        return employeeDtos;
    }

    @Override
    public EmployeeDto getById(Integer id) {
        try{
            return employeeMapper.toDto(employeeDao.findById(id).orElseThrow(Exception::new));
        }
        catch (Exception e){
            System.out.println("Employee with id = " + id + " doesn't exist");
        }
        return null;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeedto) {
        Employee employee = employeeMapper.fromDto(employeedto);
        Integer departmentId = employeedto.getDepartmentId();
        if(departmentId != null){
            try {
                Department department = departmentDao.findById(departmentId).orElseThrow(Exception::new);
                department.addEmployee(employee);
                departmentDao.save(department);
            }
            catch (Exception e){
                System.out.println("Department doesn't exist");
            }
        }
        employeeDao.save(employee);
        return employeeMapper.toDto(employee);
        /*return employeeMapper
                .toDto(employeeDao
                        .save(employeeMapper
                                .fromDto(employeedto)));*/
    }

    @Override
    public EmployeeDto update(EmployeeDto newEmployee, Integer id) {
        EmployeeDto employeeDto = getById(id);
        Employee employee = employeeMapper.fromDto(employeeDto);
        try {
            employee.setDepartment(departmentDao
                            .findById(newEmployee
                                            .getDepartmentId())
                            .orElseThrow(Exception::new));
        } catch (Exception e){
            System.out.println("Department with id = " + newEmployee.getDepartmentId()
                    + " doesn't exist. ");
        }
        employee.setSalary(newEmployee.getSalary());
        employee.setName(newEmployee.getName());
        return employeeMapper.toDto(employeeDao.save(employee));
    }

    @Override
    public void delete(Integer id) {
        employeeDao.deleteById(id);
    }
}
