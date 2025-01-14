package com.dynamicemployeemanagementsystem.domain.services;

import com.dynamicemployeemanagementsystem.common.base.BaseService;
import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.common.utilities.PageAttribute;
import com.dynamicemployeemanagementsystem.domain.dtos.RoleDto;
import com.dynamicemployeemanagementsystem.domain.entities.Role;
import com.dynamicemployeemanagementsystem.domain.mappers.RoleMapper;
import com.dynamicemployeemanagementsystem.domain.repositories.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements BaseService<RoleDto> {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Page<RoleDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<Role> rolePage = (pageableLimit) ?
                roleRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                roleRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return rolePage.map(roleMapper::map);
    }

    @Override
    public RoleDto find(Long id) throws NotFoundException {
        Role role = roleRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        return roleMapper.map(role);
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.map(null, roleDto);
        role = roleRepository.save(role);
        return roleMapper.map(role);
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) throws NotFoundException {
        Role role = roleRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        role = roleMapper.map(role, roleDto);
        role = roleRepository.save(role);
        return roleMapper.map(role);
    }

    @Override
    public String delete(Long id, Boolean softDelete) throws NotFoundException {
        Role role = roleRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        if (softDelete) {
            role.setDeleted(true);
            roleRepository.save(role);
        } else {
            roleRepository.delete(role);
        }
        return Msg.Entity.ROLE + Msg.Response.DELETE;
    }
}
