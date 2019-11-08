package pl.nitl.employeeManager.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nitl.employeeManager.exceptions.EmployeeNotFoundException;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRespository repository;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        System.out.println("adding new employee " + employee);
        repository.save(employee);
        return employee;
    }

    @PutMapping(path = "/employee", consumes = {"application/json"})
    public Employee updateOrSaveEmployee(@RequestBody Employee employee) {
        System.out.println("adding new employee " + employee);
        repository.save(employee);
        return employee;
    }

    @DeleteMapping("/employee/{eid}")
    public String deleteEmployee(@PathVariable int eid) {
        Employee e = repository.getOne(eid);
        System.out.println("adding new employee " + e);
        repository.delete(e);
        return "deleted";
    }

    @GetMapping("employees")
    public List<Employee> getEmployees() {
        System.out.println("showing all employees");
        return repository.findAll();
    }

    @GetMapping("employee/{eid}")
    public Employee getEmployee(@PathVariable("eid") int eid) {
        System.out.println("showing employee with id " + eid);
        return repository.findById(eid)
                .orElseThrow(() -> new EmployeeNotFoundException(eid));
    }

}
