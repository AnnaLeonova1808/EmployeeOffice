package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AuthorityName;
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
@Table(name = "authority")

public class Authority {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "authority_id", columnDefinition = "BINARY(16)")
    private UUID authId;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority_name")
    private AuthorityName authName;
    @ManyToMany
    @JoinTable(
            name = "role_authority", // ? какую именно таблицу, промежуточная дополнительная?
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles; //(1) Права могут быть назначены нескольким ролям

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
