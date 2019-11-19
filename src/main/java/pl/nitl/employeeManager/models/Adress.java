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
public class Adress {
    @Id
    @GeneratedValue
    private int adressId;
    //private String country;
    //private String city;
    // private String postCode;
    private AdressType adressType;
    private String street;
    private int number;
    @ManyToOne
    @JsonIgnore
    private Employee employeeRef;
}