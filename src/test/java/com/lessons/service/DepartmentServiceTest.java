package com.lessons.service;

import com.lessons.dao.DepartmentDao;
import com.lessons.dto.DepartmentDto;
import com.lessons.entity.Department;
import com.lessons.service.impl.DepartmentServiceImpl;
import com.lessons.service.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:app-context.xml"})
public class DepartmentServiceTest {

    @Mock
    private DepartmentDao departmentDao;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void getAll(){
        Department department = new Department();
        List<Department> info = new ArrayList<>();
        info.add(department);

        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentMapper.toDto(any(Department.class))).thenReturn(departmentDto);
        when(departmentDao.findAll()).thenReturn(info);

        List<DepartmentDto> departments = departmentService.getAll();

        assertNotNull(departments);
        assertEquals(1, departments.size());
    }

    @Test
    public void getById(){
        Department department = new Department();
        department.setName("depGetById");
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(department.getName());

        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(department));
        when(departmentMapper.toDto(any(Department.class))).thenReturn(departmentDto);

        DepartmentDto department1 = departmentService.getById(1);

        assertEquals("depGetById", department1.getName());
    }

    @Test
    public void create(){
        Department department = new Department();
        department.setName("depCreate");
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("depCreate");

        when(departmentDao.save(any(Department.class))).thenReturn(department);
        when(departmentMapper.fromDto(any(DepartmentDto.class))).thenReturn(department);
        when(departmentMapper.toDto(any(Department.class))).thenReturn(departmentDto);

        DepartmentDto department1 = departmentService.create(departmentDto);

        assertEquals(department.getName(), department1.getName());
    }
}
