package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
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
@Table(name = "roles")


public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.employeeOffice.generator.UuidTimeSequenceGenerator")
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RolesName roleName;

    @ManyToMany
    @JoinTable(
            name = "role_pers_info",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "personal_info")
    )
    private Set<PersonalInfo> personalInfo; //(3) каждая роль может быть назначена нескольким пользователям

    @ManyToMany(mappedBy = "role")
    private Set<Authority> auth; //(1) каждая роль может иметь несколько прав

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role roles = (Role) o;
        return Objects.equals(roleId, roles.roleId) && roleName == roles.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                ", personalInfo=" + personalInfo +
                ", auth=" + auth +
                '}';
    }
}
