package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository <Role, UUID> {
    Role getRoleByRoleId(UUID id);
    Role findByRoleName(RolesName rolesName);
}
