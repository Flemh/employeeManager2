package pl.nitl.employeeManager.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String fname;

    private String lname;

    private int pesel;

    private boolean active;

    @OneToMany(mappedBy = "employeeRef", cascade = CascadeType.ALL)
    @Singular
    private List<Address> addresses;


}

