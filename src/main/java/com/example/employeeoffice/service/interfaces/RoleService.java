package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Role;

import java.util.Set;
import java.util.UUID;

public interface RoleService {
    Role getRoleById(UUID roleId);

    Set<String> getAllRoleNames();

}
