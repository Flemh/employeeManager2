package pl.nitl.employeeManager.views;

import lombok.*;
import pl.nitl.employeeManager.models.Adress;

import javax.persistence.Entity;
import java.util.List;
@Builder
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int pesel;
    private boolean active;
    @Singular
    private List<AdressDto> adresses;


}
