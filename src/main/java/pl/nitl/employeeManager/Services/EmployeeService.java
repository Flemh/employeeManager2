package pl.nitl.employeeManager.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nitl.employeeManager.exceptions.EmployeeNotFoundException;
import pl.nitl.employeeManager.exceptions.IncorrectAdressesListSizeException;
import pl.nitl.employeeManager.exceptions.IncorrectSizeLastNameException;
import pl.nitl.employeeManager.exceptions.IncorrectZameldowaniaNumberException;
import pl.nitl.employeeManager.models.Adress;
import pl.nitl.employeeManager.models.AdressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;
import java.util.List;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRespository repository;

    public Employee checkAndSave(Employee employee) {

        employee.setActive(FALSE);
        int adressesSize = employee.getAdresses().size();

        if(adressesSize<= 0 || adressesSize>2) throw new IncorrectAdressesListSizeException(employee.getFname());
        else {
            int zameldowaniaCounter = 0;
            for (Adress a : employee.getAdresses()){
                if(a.getAdressType()== AdressType.ZAMELDOWANIA)
                zameldowaniaCounter++;
            }
            if(zameldowaniaCounter==0 || zameldowaniaCounter>1) throw new IncorrectZameldowaniaNumberException(employee.getFname());
            else {
                employee.getAdresses().forEach(adress -> adress.setEmployeeRef(employee));
                employee.setActive(TRUE);
                repository.save(employee);
                return employee;
            }
        }

    }
    public Employee chceckAndUpdate(Employee employee, int id){
        if (repository.findById(id)==null ) throw new EmployeeNotFoundException(id);
        else {
            if(employee.getEid()==0 ) employee.setEid(id);
            employee.getAdresses().forEach(adress -> adress.setEmployeeRef(employee));
            repository.save(employee);
            return employee;
        }
    }

    public void delete(int id){
        Employee e = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        if(e.getLname().length() == 5) repository.delete(e);
        else throw new IncorrectSizeLastNameException(id);
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
