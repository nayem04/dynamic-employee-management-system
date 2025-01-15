package com.dynamicemployeemanagementsystem.domain.controllers;

import com.dynamicemployeemanagementsystem.common.base.BaseController;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.domain.dtos.DepartmentDto;
import com.dynamicemployeemanagementsystem.domain.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController implements BaseController<DepartmentDto> {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(Router.Department.GET_DEPARTMENTS)
    @Override
    public ResponseEntity<Page<DepartmentDto>> search(@RequestParam(name = "query", defaultValue = "") String query,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
                                                      @RequestParam(name = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                      @RequestParam(name = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                      @RequestParam(name = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(departmentService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.Department.GET_DEPARTMENT)
    @Override
    public ResponseEntity<DepartmentDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(departmentService.find(id));
    }

    @PostMapping(Router.Department.CREATE_DEPARTMENT)
    @Override
    public ResponseEntity<DepartmentDto> create(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.save(departmentDto));
    }

    @PatchMapping(Router.Department.UPDATE_DEPARTMENT)
    @Override
    public ResponseEntity<DepartmentDto> update(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto) throws NotFoundException {
        return ResponseEntity.ok(departmentService.update(id, departmentDto));
    }

    @DeleteMapping(Router.Department.DELETE_DEPARTMENT)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(name = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(departmentService.delete(id, softDelete));
    }
}
