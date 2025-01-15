package com.dynamicemployeemanagementsystem.domain.controllers;

import com.dynamicemployeemanagementsystem.common.base.BaseController;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.domain.dtos.RoleDto;
import com.dynamicemployeemanagementsystem.domain.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController implements BaseController<RoleDto> {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(Router.Role.GET_ROLES)
    @Override
    public ResponseEntity<Page<RoleDto>> search(@RequestParam(value = "query", defaultValue = "") String query,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                                @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                @RequestParam(value = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                @RequestParam(value = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(roleService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.Role.GET_ROLE)
    @Override
    public ResponseEntity<RoleDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(roleService.find(id));
    }

    @PostMapping(Router.Role.CREATE_ROLE)
    @Override
    public ResponseEntity<RoleDto> create(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @PatchMapping(Router.Role.UPDATE_ROLE)
    @Override
    public ResponseEntity<RoleDto> update(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) throws NotFoundException {
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

    @DeleteMapping(Router.Role.DELETE_ROLE)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(value = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(roleService.delete(id, softDelete));
    }
}
