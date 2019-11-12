package pl.nitl.employeeManager.views;

import lombok.Data;

@Data
public class EmployeeDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int pesel;

}
