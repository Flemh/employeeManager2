package pl.nitl.employeeManager.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nitl.employeeManager.exceptions.*;
import pl.nitl.employeeManager.models.Address;
import pl.nitl.employeeManager.models.AddressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;

import java.util.List;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRespository repository;

    public Employee checkAndSave(Employee employee) {

        employee.setActive(FALSE);
        int addressesSize = employee.getAddresses().size();

        if(addressesSize<= 0 || addressesSize>2) {
            throw new IncorrectAddressesListSizeException(employee.getFname());
        }
        else {
            int zameldowaniaCounter = 0;
            for (Address a : employee.getAddresses()){
                if(a.getAddressType() == AddressType.ZAMELDOWANIA) {
                    zameldowaniaCounter++;
                }
            }
            if(zameldowaniaCounter == 0 || zameldowaniaCounter > 1) {
                throw new IncorrectZameldowaniaNumberException(employee.getFname());
            }
            else {
                employee.getAddresses().forEach(address -> address.setEmployeeRef(employee));
                employee.setActive(TRUE);
                return repository.save(employee);
            }
        }
    }

    public Employee checkAndUpdate(Employee employee, int id){
        if(repository.findById(id).isEmpty()){
            throw new EmployeeNotFoundException(id);
        }
        else {
            employee.setEid(id);
            employee.getAddresses().forEach(address -> address.setEmployeeRef(employee));
            repository.save(employee);
            return employee;
        }
    }

    public void delete(int id){
        Employee e = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
            repository.delete(e);
    }

    public void deleteWith5(){
        List<Employee> allEmployees = repository.findAll();

        if (allEmployees == null) {
            throw new EmptyListException();
        }
        else{
            boolean thereIsNoOneWith5 = TRUE;

            for (Employee e : allEmployees){
                if(e.getLname().length() == 5) {
                    repository.delete(e);
                    thereIsNoOneWith5 = FALSE;
                }
            }
            if (thereIsNoOneWith5) {
                throw new ThereIsNoOneWith5Exception();
            }
        }
    }

    public List<Employee> showAll(){
        log.info("showing all employees");
        return repository.findAll();
    }

    public Employee showOne(int id){
        log.info("showing employee with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
