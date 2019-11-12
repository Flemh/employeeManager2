package pl.nitl.employeeManager.views;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int pesel;

}
