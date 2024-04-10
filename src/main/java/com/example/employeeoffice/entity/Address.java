package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AddressType;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "apart_number")
    private String apartNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pers_info_id")
    private PersonalInfo personalInfo; // (8) ссылка на сущность PersonalInfo, к которому относится данный адрес.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(apartNumber, address.apartNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, houseNumber, apartNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", apartNumber='" + apartNumber + '\'' +
                ", addressType=" + addressType +
                ", personalInfo=" + personalInfo +
                '}';
    }
}
