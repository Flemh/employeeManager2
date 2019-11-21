package pl.nitl.employeeManager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private int addressId;

    private AddressType addressType;

    private String street;

    private int number;

    @ManyToOne
    @JsonIgnore
    private Employee employeeRef;
}