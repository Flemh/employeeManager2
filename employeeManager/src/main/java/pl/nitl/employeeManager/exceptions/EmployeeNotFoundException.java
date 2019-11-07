package pl.nitl.employeeManager.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int eid) {
        super ("Could not found employee with id"+eid);
    }
}
