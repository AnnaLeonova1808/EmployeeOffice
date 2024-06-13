package com.example.employeeoffice.entity;

import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.example.employeeoffice.validation.annotation.EmailChecker;
import com.example.employeeoffice.validation.annotation.PhoneNumberChecker;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Relation;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Entity class for Personal Information.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personal_info")
@Schema(description = "Personal information of an employee")
public class PersonalInfo {

    /**
     * Unique identifier for the personal information.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "pers_info_id")
    @Schema(description = "Unique identifier of the personal information")
    private UUID persInfoId;

    /**
     * Username of the person.
     */
    @Column(name = "username")
    @Schema(description = "Username of the person")
    private String username;

    /**
     * Birthday of the person.
     */
    @Column(name = "birthday")
    @Schema(description = "Birthday of the person")
    private LocalDate birthday;

    /**
     * Phone number of the person.
     */
    @PhoneNumberChecker
    @Column(name = "phone_number")
    @Schema(description = "Phone number of the person")
    private String phoneNumber;

    /**
     * Email of the person.
     */
        @EmailChecker
    @Column(name = "email")
        @Schema(description = "Email of the person")
    private String email;

    /**
     * Password for the person's account.
     */
    @Column(name = "password")
    @Schema(description = "Password for the person's account")
    private String password;

    /**
     * Salary of the person.
     */
    @Column(name = "salary")
    @Schema(description = "Salary of the person")
    private double salary;

    /**
     * Addresses associated with the person.
     * Each instance of PersonalInfo can have multiple addresses (e.g., work and home).
     */
    @OneToMany(mappedBy = "personalInfo", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @Schema(description = "Addresses associated with the person")
    private Set<Address> addresses;

    /**
     * Roles associated with the person.
     * One user (employee) can have multiple roles.
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pers_info_role",
            joinColumns = @JoinColumn(name = "pers_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Schema(description = "Roles associated with the person")
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalInfo)) return false;
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(persInfoId, that.persInfoId) && Objects.equals(birthday, that.birthday)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persInfoId, birthday, phoneNumber);
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "persInfoId=" + persInfoId +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", addresses=" + addresses +
                ", roles=" + roles +
                '}';
    }
}
