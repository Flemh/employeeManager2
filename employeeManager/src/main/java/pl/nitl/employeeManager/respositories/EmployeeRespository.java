package pl.nitl.employeeManager.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nitl.employeeManager.models.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
}
