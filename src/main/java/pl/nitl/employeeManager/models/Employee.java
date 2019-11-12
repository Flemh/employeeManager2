package pl.nitl.employeeManager.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int eid;
    private String fname;
    private String lname;
    private int pesel;
}

