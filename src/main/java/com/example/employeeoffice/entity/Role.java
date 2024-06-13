package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
/**
 * Entity class for Role.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Schema(description = "Role assigned to users, containing a set of authorities")
public class Role {
    /**
     * Unique identifier for the role.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "role_id")
    @Schema(description = "Unique identifier of the role")
    private UUID roleId;

    /**
     * Name of the role.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    @Schema(description = "Name of the role")
    private RolesName roleName;

    /**
     * Personal information of users assigned to this role.
     * Each role can be assigned to multiple users.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @Schema(description = "Users assigned to this role")
    private Set<PersonalInfo> personalInfo;

    /**
     * Authorities associated with this role.
     * Each role can have multiple authorities.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    @Schema(description = "Authorities assigned to this role")
    private Set<Authority> authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                ", personalInfo=" + personalInfo +
                ", authorities=" + authorities +
                '}';
    }
}
