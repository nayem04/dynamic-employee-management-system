package com.dynamicemployeemanagementsystem.domain.services;

import com.dynamicemployeemanagementsystem.common.base.BaseService;
import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.common.utilities.PageAttribute;
import com.dynamicemployeemanagementsystem.domain.dtos.EmployeeDto;
import com.dynamicemployeemanagementsystem.domain.entities.Employee;
import com.dynamicemployeemanagementsystem.domain.mappers.EmployeeMapper;
import com.dynamicemployeemanagementsystem.domain.repositories.EmployeeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements BaseService<EmployeeDto> {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<EmployeeDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<Employee> employeePage = (pageableLimit) ?
                employeeRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                employeeRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return employeePage.map(employeeMapper::map);
    }

    @Override
    @Cacheable(value = Msg.Entity.EMPLOYEE, key = "#id", unless = "#result == null")
    public EmployeeDto find(Long id) throws NotFoundException {
        Employee employee = employeeRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.EMPLOYEE, id));
        return employeeMapper.map(employee);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) throws NotFoundException {
        Employee employee = employeeMapper.map(null, employeeDto);
        employee = employeeRepository.save(employee);
        return employeeMapper.map(employee);
    }

    @Override
    @CachePut(value = Msg.Entity.EMPLOYEE, key = "#id")
    public EmployeeDto update(Long id, EmployeeDto employeeDto) throws NotFoundException {
        Employee employee = employeeRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.EMPLOYEE, id));
        employee = employeeMapper.map(employee, employeeDto);
        employee = employeeRepository.save(employee);
        return employeeMapper.map(employee);
    }

    @Override
    @CacheEvict(value = Msg.Entity.EMPLOYEE, key = "#id")
    public String delete(Long id, Boolean softDelete) throws NotFoundException {
        Employee employee = employeeRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.EMPLOYEE, id));
        if (softDelete) {
            employee.setDeleted(true);
            employeeRepository.save(employee);
        } else {
            employeeRepository.delete(employee);
        }
        return Msg.Entity.EMPLOYEE + Msg.Response.DELETE;
    }

    @CacheEvict(value = Msg.Entity.EMPLOYEE, allEntries = true)
    public String deleteEmployeesCaches() {
        return Msg.Entity.EMPLOYEE + Msg.Response.CACHES + Msg.Response.CLEAR;
    }
}
