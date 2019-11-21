package pl.nitl.employeeManager.exceptions;

public class IncorrectZameldowaniaNumberException extends RuntimeException {

    public IncorrectZameldowaniaNumberException(String fname) {
        super ("there is not zameldowania address or there is too many zameldowania addresses for employee "+fname);
    }
}
