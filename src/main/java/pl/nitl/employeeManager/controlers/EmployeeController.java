package pl.nitl.employeeManager.controlers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nitl.employeeManager.exceptions.EmployeeNotFoundException;
import pl.nitl.employeeManager.mappers.EmployeeMapper;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;
import pl.nitl.employeeManager.views.EmployeeDto;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRespository repository;
    private EmployeeMapper mapper
            = Mappers.getMapper(EmployeeMapper.class);

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        System.out.println("adding new employee " + employeeDto + "and mapping it to " + mappedEmployee);
        repository.save(mappedEmployee);
        return mappedEmployee;
    }

    @PutMapping(path = "/employee", consumes = {"application/json"})
    public Employee updateOrSaveEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        System.out.println("adding new employee " + employeeDto + "and mapping it to " + mappedEmployee);
        repository.save(mappedEmployee);
        return mappedEmployee;
    }

    @DeleteMapping("/employee/{eid}")
    public String deleteEmployee(@PathVariable int eid) {

        Employee e = repository.findById(eid)
                .orElseThrow(() -> new EmployeeNotFoundException(eid));
        System.out.println("adding new employee " + e);
        repository.delete(e);
        return "deleted";
    }

    @GetMapping("employees")
    public List<Employee> getEmployees() {
        System.out.println("Ten kometarz ma zostac");
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
