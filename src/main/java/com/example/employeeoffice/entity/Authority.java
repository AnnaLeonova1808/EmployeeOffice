package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AuthorityName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents an authority in the employee office system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "authorities")
@Schema(description = "Represents an authority in the employee office system.")
public class Authority {

    /**
     * Unique identifier of the authority.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "auth_id")
    @Schema(description = "Unique identifier of the authority")
    private UUID authId;

    /**
     * Name of the authority.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    @Schema(description = "Name of the authority")
    private AuthorityName authority;

    /**
     * Roles associated with this authority.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @Schema(description = "Roles associated with this authority")
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return authId.equals(authority1.authId) && authority == authority1.authority;
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
