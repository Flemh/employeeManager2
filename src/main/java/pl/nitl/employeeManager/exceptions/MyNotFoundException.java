package pl.nitl.employeeManager.exceptions;

public class MyNotFoundException extends RuntimeException {
    public MyNotFoundException(String message) {
        super("NotFoundException: " + message);
    }
}

