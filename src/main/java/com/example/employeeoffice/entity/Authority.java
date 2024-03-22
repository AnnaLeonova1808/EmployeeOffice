package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AuthorityName;
import com.example.employeeoffice.generator.UuidGenerator;
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
@Table(name = "authorities")

public class Authority {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", type = UuidGenerator.class)
    @Column(name = "auth_id")
    private UUID authId;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_name")
    private AuthorityName authName;

    @ManyToMany
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; //(1) Права могут быть назначены нескольким ролям

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority = (Authority) o;
        return Objects.equals(authId, authority.authId) && authName == authority.authName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authId, authName);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authId=" + authId +
                ", authName=" + authName +
                ", roles=" + roles +
                '}';
    }
}
