package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository <Role, UUID> {
    Role getRoleByRoleId(UUID id);
    Optional<Role> findByRoleName(RolesName roleName);
    List<Role> findAll();
    Role getRoleByRoleName(RolesName roleName);

}
