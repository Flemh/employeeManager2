package pl.nitl.employeeManager.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nitl.employeeManager.exceptions.EmployeeNotFoundException;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRespository repository;



    //@PostMapping(path="/employees",consumes = {"application/xml"}) get produces, post consumes
    @PostMapping("/employee")//@RequestBody potrzebne jest gdy chcemy przesylac bezposredniio json a nie za pomoca form
    public Employee addEmployee(@RequestBody Employee employee) {
        repository.save(employee);
        return employee;
    }

    @PutMapping(path="/employee",consumes = {"application/json"})
    public Employee updateOrSaveEmployee(@RequestBody Employee employee) {
        repository.save(employee);
        return employee;
    }


    @DeleteMapping("/employee/{eid}")
    public String deleteEmployee(@PathVariable int eid){
        Employee e = repository.getOne(eid);
        repository.delete(e);
        return "deleted";
    }



    @GetMapping("employees")
    public List<Employee> getEmployees(){


        return repository.findAll();
    }

    @GetMapping("employee/{eid}")
    public Employee getEmployee(@PathVariable("eid") int eid){


        return repository.findById(eid)
                .orElseThrow(() -> new EmployeeNotFoundException(eid));
    }


}
