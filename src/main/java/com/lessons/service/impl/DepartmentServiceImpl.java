package com.lessons.service.impl;

import com.lessons.dao.DepartmentDao;
import com.lessons.dto.DepartmentDto;
import com.lessons.entity.Department;
import com.lessons.service.DepartmentService;
import com.lessons.service.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao, DepartmentMapper departmentMapper) {
        this.departmentDao = departmentDao;
        this.departmentMapper = departmentMapper;
    }


    public List<DepartmentDto> getAll() {
        List<Department> departments = departmentDao.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        if(departments != null)
            departments.forEach(x->departmentDtos.add(departmentMapper.toDto(x)));
        return departmentDtos;
    }

    public DepartmentDto getById(Integer id){
        try {
            return departmentMapper
                    .toDto(departmentDao
                            .findById(id)
                            .orElseThrow(Exception::new));
        }
        catch (Exception e){
            System.out.println("Department with id = " + id + " doesn't exist");
        }
        return null;
    }

    public DepartmentDto create(DepartmentDto departmentDto) {
        return departmentMapper
                .toDto(departmentDao
                        .save(departmentMapper
                                .fromDto(departmentDto)));
    }

    public DepartmentDto update(DepartmentDto newDepartment, Integer id) {
        DepartmentDto departmentDto = getById(id);
        Department department = departmentMapper.fromDto(departmentDto);
        department.setId(departmentDto.getId());
        department.setName(newDepartment.getName());
        return departmentMapper
                .toDto(departmentDao
                        .save(department));
    }

    public void delete(Integer id) {
        departmentDao.deleteById(id);
    }
}
