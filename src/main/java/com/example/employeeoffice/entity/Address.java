package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.AddressType;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;
/**
 * Represents an address entity in the employee office system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "addresses")
@Schema(description = "Represents an address entity in the employee office system.")
public class Address {
    /**
     * Unique identifier of the address.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "address_id")
    @Schema(description = "Unique identifier of the address")
    private UUID addressId;

    /**
     * Country of the address.
     */
    @Column(name = "country")
    @Schema(description = "Country of the address")
    private String country;

    /**
     * City of the address.
     */
    @Column(name = "city")
    @Schema(description = "City of the address")
    private String city;

    /**
     * Street of the address.
     */
    @Column(name = "street")
    @Schema(description = "Street of the address")
    private String street;

    /**
     * House number of the address.
     */
    @Column(name = "house_number")
    @Schema(description = "House number of the address")
    private String houseNumber;

    /**
     * Apartment number of the address.
     */
    @Column(name = "apart_number")
    @Schema(description = "Apartment number of the address")
    private String apartNumber;

    /**
     * Type of the address.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    @Schema(description = "Type of the address")
    private AddressType addressType;

    /**
     * Personal information associated with the address.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pers_info_id")
    @Schema(description = "Personal information associated with the address")
    private PersonalInfo personalInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && Objects.equals(street, address.street) && Objects.equals
                (houseNumber, address.houseNumber) && Objects.equals(apartNumber, address.apartNumber);
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
