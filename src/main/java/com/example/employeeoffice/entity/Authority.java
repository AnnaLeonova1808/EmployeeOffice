package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AuthorityName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.*;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "authId")
public class Authority {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "auth_id")
    private UUID authId;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private AuthorityName authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<Role> roles; //(1) Права могут быть назначены нескольким ролям

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authId, authority1.authId) && authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authId, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authId=" + authId +
                ", authority=" + authority +
                ", roles=" + roles +
                '}';
    }
}
