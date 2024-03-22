package com.example.employeeoffice.entity;

import com.example.employeeoffice.generator.UuidGenerator;
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
    @GenericGenerator(name = "uuid", type = UuidGenerator.class)
    @Column(name = "pers_info_id")
    private UUID persInfoId;

    @OneToOne
    @JoinColumn(name = "emp_id") // (2) у каждого сотрудника может быть только одна личная информация.
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

    @OneToOne
    @JoinColumn(name = "office_address_id")
    private Address officeAddress;

    @ManyToMany
    @JoinTable(name = "pers_info_roles") // (3) Один пользователь (сотрудник) может иметь несколько ролей
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
                ", empId=" + empId +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", homeAddress=" + homeAddress +
                ", officeAddress=" + officeAddress +
                ", roles=" + roles +
                '}';
    }
}
