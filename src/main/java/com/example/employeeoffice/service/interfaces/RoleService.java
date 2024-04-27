package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.RoleAlreadyExistException;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role getRoleById(UUID id);

    Role getRoleByRoleName(RolesName roleName);

    List<Role> getAllRoles();
}
