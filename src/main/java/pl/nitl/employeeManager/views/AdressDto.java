package pl.nitl.employeeManager.views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.nitl.employeeManager.models.AdressType;

import javax.persistence.Entity;


@Builder
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class AdressDto {
    private int adressId;
    //private String country;
    //private String city;
    // private String postCode;
    private AdressType adressType;
    private String street;
    private int number;

}
