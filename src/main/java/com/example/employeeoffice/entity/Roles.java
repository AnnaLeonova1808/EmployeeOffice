package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.RolesName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")


public class Roles {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "role_id", columnDefinition = "BINARY(16)")
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RolesName roleName;
    @ManyToMany
    @JoinTable(
            name = "role_personal_info", // ? как прописать таблицы, если одна из таблиц через _ название?
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "personal_info")
    )
    private Set<PersonalInfo> personalInfo; //(3) каждая роль может быть назначена нескольким пользователям
    @ManyToMany(mappedBy = "role")
    private Set<Authority> auth; //(1) каждая роль может иметь несколько прав

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        Roles roles = (Roles) o;
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
