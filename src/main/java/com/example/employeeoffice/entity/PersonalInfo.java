package com.example.employeeoffice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ropersonal_info")

public class PersonalInfo {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "personal_info_id", columnDefinition = "BINARY(16)")
    private UUID persInfoId;
    @OneToOne
    @JoinColumn(name = "employee_id") // (2) у каждого сотрудника может быть только одна личная информация.
    private Employee empId;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "salary")
    private double salary;
    @OneToOne
    @JoinColumn(name = "home_address_id")
    private Address homeAddress;
    @ManyToMany
    @JoinTable(name = "personal_info_roles") // (3) Один пользователь (сотрудник) может иметь несколько ролей
    private Set<Roles> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalInfo)) return false;
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(persInfoId, that.persInfoId) && Objects.equals(birthday, that.birthday) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persInfoId, birthday, phoneNumber);
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "persInfoId=" + persInfoId +
                ", empId=" + empId +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", homeAddress=" + homeAddress +
                ", roles=" + roles +
                '}';
    }
}
