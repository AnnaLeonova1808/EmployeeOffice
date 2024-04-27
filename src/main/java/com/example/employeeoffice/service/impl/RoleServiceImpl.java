package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.RoleAlreadyExistException;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public final RoleRepository roleRepository;

    @Override
    public Role getRoleById(UUID roleId) {
        Role role = roleRepository.getRoleByRoleId(roleId);
        if (role != null) {
            return role;
        } else {
            throw new RoleAlreadyExistException(ErrorMessage.ROLE_ALREADY_EXIST_EXCEPTION);
        }
    }

    @Override
    public Role getRoleByRoleName(RolesName roleName) {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .filter(role -> role.getRoleName().equals(roleName))
                .limit(1)
                .findAny()
                .orElseThrow(() -> new RoleAlreadyExistException(ErrorMessage.ROLE_ALREADY_EXIST_EXCEPTION));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
