package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.RoleIdNotFoundException;
import com.example.employeeoffice.exception.RoleNotFoundException;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public final RoleRepository roleRepository;

    public final PersonalInfoRepository personalInfoRepository;

    @Override
    public Role getRoleById(UUID roleId) {

        Role role = roleRepository.getRoleByRoleId(roleId);
        if (role == null) {
            throw new RoleIdNotFoundException(ErrorMessage.ROLE_ID_NOT_FOUND_EXCEPTION);
        }
        return role;
    }

    @Override
    public Set<String> getAllRoleNames() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new RoleNotFoundException(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION);
        }
        return roles.stream()
                .map(Role::getRoleName)
                .map(RolesName::name)
                .collect(Collectors.toSet());
    }
}


