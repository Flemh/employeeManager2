package pl.nitl.employeeManager.views;

import lombok.*;
import pl.nitl.employeeManager.models.AddressType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private int addressId;

    private AddressType addressType;

    private String street;

    private int number;
}
