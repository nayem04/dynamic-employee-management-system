package com.dynamicemployeemanagementsystem.domain.controllers;

import com.dynamicemployeemanagementsystem.common.base.BaseController;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.exceptions.NullPasswordException;
import com.dynamicemployeemanagementsystem.common.exceptions.UserAlreadyExistsException;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.domain.dtos.UserDto;
import com.dynamicemployeemanagementsystem.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements BaseController<UserDto> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Router.User.GET_USERS)
    @Override
    public ResponseEntity<Page<UserDto>> search(@RequestParam(name = "query", defaultValue = "") String query,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                @RequestParam(name = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                @RequestParam(name = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                @RequestParam(name = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(userService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.User.GET_USER)
    @Override
    public ResponseEntity<UserDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.find(id));
    }

    @PostMapping(Router.User.CREATE_USER)
    @Override
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) throws NotFoundException, NullPasswordException, UserAlreadyExistsException {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PatchMapping(Router.User.UPDATE_USER)
    @Override
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) throws NotFoundException, NullPasswordException, UserAlreadyExistsException {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping(Router.User.DELETE_USER)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(name = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(userService.delete(id, softDelete));
    }
}
