package pl.nitl.employeeManager.exceptions;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("List of Employees is empty ");
    }
}
