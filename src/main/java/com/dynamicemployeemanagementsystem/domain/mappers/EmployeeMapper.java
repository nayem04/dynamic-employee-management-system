package com.dynamicemployeemanagementsystem.domain.mappers;

import com.dynamicemployeemanagementsystem.common.base.BaseMapper;
import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.domain.dtos.EmployeeDto;
import com.dynamicemployeemanagementsystem.domain.entities.Department;
import com.dynamicemployeemanagementsystem.domain.entities.Employee;
import com.dynamicemployeemanagementsystem.domain.entities.User;
import com.dynamicemployeemanagementsystem.domain.repositories.DepartmentRepository;
import com.dynamicemployeemanagementsystem.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements BaseMapper<Employee, EmployeeDto> {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public EmployeeMapper(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EmployeeDto map(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setCreated(employee.getCreated());
        employeeDto.setLastUpdated(employee.getLastUpdated());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setDateOfJoining(employee.getDateOfJoining());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setAdditionalAttributes(employee.getAdditionalAttributes());
        employeeDto.setDepartmentId(employee.getDepartment().getId());
        employeeDto.setUserId(employee.getUser().getId());
        return employeeDto;
    }

    @Override
    public Employee map(Employee employee, EmployeeDto employeeDto) throws NotFoundException {
        Department department = departmentRepository.find(employeeDto.getDepartmentId())
                .orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.DEPARTMENT, employeeDto.getDepartmentId()));

        User user = userRepository.find(employeeDto.getUserId())
                .orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, employeeDto.getUserId()));

        if (employee == null) {
            employee = new Employee();
        }

        employee.setDesignation(employeeDto.getDesignation());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setSalary(employeeDto.getSalary());
        employee.setAdditionalAttributes(employeeDto.getAdditionalAttributes());
        employee.setDepartment(department);
        employee.setUser(user);
        return employee;
    }
}
