package com.dynamicemployeemanagementsystem.domain.mappers;

import com.dynamicemployeemanagementsystem.common.base.BaseMapper;
import com.dynamicemployeemanagementsystem.domain.dtos.DepartmentDto;
import com.dynamicemployeemanagementsystem.domain.entities.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements BaseMapper<Department, DepartmentDto> {
    @Override
    public DepartmentDto map(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setCreated(department.getCreated());
        departmentDto.setLastUpdated(department.getLastUpdated());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());
        return departmentDto;
    }

    @Override
    public Department map(Department department, DepartmentDto departmentDto) {
        if(department == null){
            department = new Department();
        }
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        return department;
    }
}
