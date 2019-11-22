package pl.nitl.employeeManager.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nitl.employeeManager.exceptions.*;
import pl.nitl.employeeManager.models.AddressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    private int getLength(Employee employee) {
        return employee.getLname().length();
    }

    public Employee checkAndSave(Employee employee) {

        employee.setActive(FALSE);
        int addressesSize = employee.getAddresses().size();

        if (addressesSize <= 0 || addressesSize > 2) {
            throw new BadRequestException("incorrect quantity of addresses (0 or >2) for Employee: " + employee.getFname());
        }
        long counter = employee.getAddresses().stream().filter(address -> address.getAddressType() == AddressType.INVOICING).count();

        if (counter != 1) {
            throw new BadRequestException("incorrect quantity of Invoicing type addresses (0 or >1) for Employee: " + employee.getFname());
        }
        employee.getAddresses().forEach(address -> address.setEmployeeRef(employee));
        employee.setActive(TRUE);
        return repository.save(employee);
    }

    public Employee checkAndUpdate(Employee employee, int id) {
        if (repository.findById(id).isEmpty()) {
            throw new MyNotFoundException("Could not find Employee with id: " + id);
        }

        employee.setEid(id);
        employee.getAddresses().forEach(address -> address.setEmployeeRef(employee));
        return repository.save(employee);
    }

    public void delete(int id) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Could not find Employee with id: " + id));
        repository.delete(e);
    }

    public void deleteWith5() {
        List<Employee> allEmployees = repository.findAll();

        if (allEmployees.isEmpty()) {
            throw new MyNotFoundException("List of Employees to delete is empty");
        }

        List<Employee> employeeList = allEmployees.stream().filter(employee -> getLength(employee) == 5)
                .collect(Collectors.toList());
        if (employeeList.isEmpty()) {
            throw new BadRequestException("There is no one with 5 letter last name");
        }
        employeeList.forEach(employee -> repository.delete(employee));
    }

    public List<Employee> showAll() {
        log.info("showing all employees");
        return repository.findAll();
    }

    public Employee showOne(int id) {
        log.info("showing employee with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Could not find Employee with id: " + id));
    }
}
