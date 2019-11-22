package pl.nitl.employeeManager.controlers;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nitl.employeeManager.Services.EmployeeService;
import pl.nitl.employeeManager.mappers.EmployeeMapper;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.EmployeeDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    private EmployeeMapper mapper
            = Mappers.getMapper(EmployeeMapper.class);

    @PostMapping("/employee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        log.info("adding new employee {} and mapping it to {}", employeeDto, mappedEmployee);
        Employee newEmployee = service.checkAndSave(mappedEmployee);
        return mapper.employeeToEmployeeDto(newEmployee);
    }

    @PutMapping(path = "/employee/{eid}", consumes = {"application/json"})
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable("eid") int eid) {
        Employee mappedEmployee = mapper.employeeDtoToEmployee(employeeDto);
        log.info("adding new employee {} and mapping it to {}", employeeDto, mappedEmployee);
        Employee updatedEmployee = service.checkAndUpdate(mappedEmployee, eid);
        return mapper.employeeToEmployeeDto(updatedEmployee);
    }

    @DeleteMapping("/employee/{eid}")
    public void deleteEmployee(@PathVariable("eid") int eid) {
       service.delete(eid);
    }

    @DeleteMapping("/employees")
    public void deleteEmployeeWith5SignsLastName() {
        service.deleteWith5();
    }

    @GetMapping("employees")
    public List<EmployeeDto> getEmployees() {
        List<Employee> employeeList = service.showAll();
        return employeeList.stream()
                .map(employee -> mapper.employeeToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @GetMapping("employee/{eid}")
    public EmployeeDto getEmployee(@PathVariable("eid") int eid) {
        Employee employeeToShow = service.showOne(eid);
        return mapper.employeeToEmployeeDto(employeeToShow);
    }

}
