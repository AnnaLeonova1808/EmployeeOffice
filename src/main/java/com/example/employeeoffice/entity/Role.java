package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roleId")
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RolesName roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<PersonalInfo> personalInfo; //(3) каждая роль может быть назначена нескольким пользователям

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private Set<Authority> authorities; //(1) каждая роль может иметь несколько прав

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
