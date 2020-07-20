package com.lessons.service;

import com.lessons.dto.DepartmentDto;
import com.lessons.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<DepartmentDto> getAll();

    public DepartmentDto getById(Integer id);

    public DepartmentDto create(DepartmentDto department);

    public DepartmentDto update(DepartmentDto newDepartment, Integer id);

    public void delete(Integer id);
}
