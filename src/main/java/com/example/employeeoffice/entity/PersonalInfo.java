package com.example.employeeoffice.entity;

import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personal_info")
public class PersonalInfo {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "pers_info_id")
    private UUID persInfoId;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "salary")
    private double salary;

    @JsonIgnore
    @OneToOne(mappedBy = "persInfo",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // (2) у каждого сотрудника может быть только одна личная информация.
    @JoinColumn(name = "emp_id")
    private Employee employee;
    @JsonIgnore
    @OneToMany(mappedBy = "personalInfo", fetch = FetchType.LAZY)
    // 8)каждый экземпляр PersonalInfo имеет  не один адрес (рабочий и домашний).
    private Set<Address> addresses;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pers_info_role",
            joinColumns = @JoinColumn(name = "pers_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
// (3) Один пользователь (сотрудник) может иметь несколько ролей
    private Set<Role> roles;

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
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", employee=" + employee +
                ", addresses=" + addresses +
                ", roles=" + roles +
                '}';
    }
}
