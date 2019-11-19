package pl.nitl.employeeManager.controlers;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nitl.employeeManager.Services.EmployeeService;
import pl.nitl.employeeManager.mappers.EmployeeMapper;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.EmployeeDto;
import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;
    private EmployeeMapper mapper
            = Mappers.getMapper(EmployeeMapper.class);

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        log.info("adding new employee {} and mapping it to {}", employeeDto, mappedEmployee);
        Employee newEmployee = service.checkAndSave(mappedEmployee);
        return newEmployee;
    }

    @PutMapping(path = "/employee/{eid}", consumes = {"application/json"})
    public Employee updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable int eid) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        log.info("adding new employee {} and mapping it to {}", employeeDto, mappedEmployee);
        Employee updatedEmployee = service.chceckAndUpdate(mappedEmployee, eid);
        return updatedEmployee;
    }

    @DeleteMapping("/employee/{eid}")
    public void deleteEmployee(@PathVariable("eid") int eid) {
       service.delete(eid);
    }

    @GetMapping("employees")
    public List<Employee> getEmployees() {
     return service.showAll();
    }

    @GetMapping("employee/{eid}")
    public Employee getEmployee(@PathVariable("eid") int eid) {
       return service.showOne(eid);
    }

}
