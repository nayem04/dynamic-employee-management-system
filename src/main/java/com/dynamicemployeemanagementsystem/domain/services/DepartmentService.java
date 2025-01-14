package com.dynamicemployeemanagementsystem.domain.services;

import com.dynamicemployeemanagementsystem.common.base.BaseService;
import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.common.utilities.PageAttribute;
import com.dynamicemployeemanagementsystem.domain.dtos.DepartmentDto;
import com.dynamicemployeemanagementsystem.domain.entities.Department;
import com.dynamicemployeemanagementsystem.domain.mappers.DepartmentMapper;
import com.dynamicemployeemanagementsystem.domain.repositories.DepartmentRepository;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Setter
public class DepartmentService implements BaseService<DepartmentDto> {
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<DepartmentDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<Department> departmentPage = (pageableLimit) ?
                departmentRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                departmentRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return departmentPage.map(departmentMapper::map);
    }

    @Override
    public DepartmentDto find(Long id) throws NotFoundException {
        Department department = departmentRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.DEPARTMENT, id));
        return departmentMapper.map(department);
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentMapper.map(null, departmentDto);
        departmentRepository.save(department);
        return departmentMapper.map(department);
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto departmentDto) throws NotFoundException {
        Department department = departmentRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.DEPARTMENT, id));
        department = departmentMapper.map(department, departmentDto);
        departmentRepository.save(department);
        return departmentMapper.map(department);
    }

    @Override
    public String delete(Long id, Boolean softDelete) throws NotFoundException {
        Department department = departmentRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.DEPARTMENT, id));
        if (softDelete) {
            department.setDeleted(true);
            departmentRepository.save(department);
        } else {
            departmentRepository.delete(department);
        }
        return Msg.Entity.DEPARTMENT + Msg.Response.DELETE;
    }
}
