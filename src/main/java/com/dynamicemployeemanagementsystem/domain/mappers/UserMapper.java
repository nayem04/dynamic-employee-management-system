package com.dynamicemployeemanagementsystem.domain.mappers;

import com.dynamicemployeemanagementsystem.common.base.BaseMapper;
import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.enums.RoleEnum;
import com.dynamicemployeemanagementsystem.common.exceptions.NotFoundException;
import com.dynamicemployeemanagementsystem.common.exceptions.NullPasswordException;
import com.dynamicemployeemanagementsystem.common.exceptions.UserAlreadyExistsException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.common.utilities.PasswordUtil;
import com.dynamicemployeemanagementsystem.domain.dtos.UserDto;
import com.dynamicemployeemanagementsystem.domain.entities.Role;
import com.dynamicemployeemanagementsystem.domain.entities.User;
import com.dynamicemployeemanagementsystem.domain.repositories.RoleRepository;
import com.dynamicemployeemanagementsystem.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements BaseMapper<User, UserDto> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserMapper(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreated(user.getCreated());
        userDto.setLastUpdated(user.getLastUpdated());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRoleIds(
                user.getRoles().stream().map(Role::getId).collect(Collectors.toList())
        );
        return userDto;
    }

    @Override
    public User map(User user, UserDto userDto) throws NullPasswordException, NotFoundException, UserAlreadyExistsException {
        if (user == null) {
            if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw ExceptionUtil.getUserAlreadyExistsException("Username", userDto.getUsername());
            }
            user = new User();
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(PasswordUtil.encryptPassword(userDto.getPassword()));
        List<Role> roles = roleRepository.findByIds(userDto.getRoleIds());
        if (roles.isEmpty()) {
            Role role = roleRepository.find(RoleEnum.ROLE_EMPLOYEE.getValue().longValue())
                    .orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE,
                            RoleEnum.ROLE_EMPLOYEE.getValue().longValue()));
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
}