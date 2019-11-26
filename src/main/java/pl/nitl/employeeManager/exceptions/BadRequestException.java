package pl.nitl.employeeManager.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("BadRequestException: " + message);
    }
}
