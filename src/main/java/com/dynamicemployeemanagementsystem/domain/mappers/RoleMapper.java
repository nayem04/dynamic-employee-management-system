package com.dynamicemployeemanagementsystem.domain.mappers;

import com.dynamicemployeemanagementsystem.common.base.BaseMapper;
import com.dynamicemployeemanagementsystem.domain.dtos.RoleDto;
import com.dynamicemployeemanagementsystem.domain.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements BaseMapper<Role, RoleDto> {
    @Override
    public RoleDto map(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setCreated(role.getCreated());
        roleDto.setLastUpdated(role.getLastUpdated());
        roleDto.setLabel(role.getLabel());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }

    @Override
    public Role map(Role role, RoleDto roleDto) {
        if (role == null) {
            role = new Role();
        }
        role.setLabel(roleDto.getLabel());
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
    }
}
