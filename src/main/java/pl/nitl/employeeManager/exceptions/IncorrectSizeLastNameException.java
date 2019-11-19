package pl.nitl.employeeManager.exceptions;

public class IncorrectSizeLastNameException extends RuntimeException {
    public IncorrectSizeLastNameException(int eid) {
        super ("Could not delete employee with id "+eid);
    }
}
