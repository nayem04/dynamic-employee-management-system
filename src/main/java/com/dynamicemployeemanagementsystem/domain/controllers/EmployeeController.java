package com.dynamicemployeemanagementsystem.domain.controllers;

import com.dynamicemployeemanagementsystem.common.base.BaseController;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.domain.dtos.EmployeeDto;
import com.dynamicemployeemanagementsystem.domain.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController implements BaseController<EmployeeDto> {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(Router.Employee.GET_EMPLOYEES)
    @Override
    public ResponseEntity<Page<EmployeeDto>> search(@RequestParam(name = "query", defaultValue = "") String query,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
                                                    @RequestParam(name = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                    @RequestParam(name = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                    @RequestParam(name = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(employeeService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.Employee.GET_EMPLOYEE)
    @Override
    public ResponseEntity<EmployeeDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(employeeService.find(id));
    }

    @PostMapping(Router.Employee.CREATE_EMPLOYEE)
    @Override
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeDto) throws NotFoundException {
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }

    @PatchMapping(Router.Employee.UPDATE_EMPLOYEE)
    @Override
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @Valid @RequestBody EmployeeDto employeeDto) throws NotFoundException {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }

    @DeleteMapping(Router.Employee.DELETE_EMPLOYEE)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(name = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(employeeService.delete(id, softDelete));
    }

    @DeleteMapping(Router.Employee.DELETE_EMPLOYEES_CACHES)
    public ResponseEntity<String> deleteEmployeesCaches() {
        return ResponseEntity.ok(employeeService.deleteEmployeesCaches());
    }
}
