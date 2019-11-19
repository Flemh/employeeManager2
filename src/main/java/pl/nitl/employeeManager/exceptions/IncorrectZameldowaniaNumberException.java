package pl.nitl.employeeManager.exceptions;

public class IncorrectZameldowaniaNumberException extends RuntimeException {
    public IncorrectZameldowaniaNumberException(String fname) {
        super ("there is not zameldowania adress or there is too many zameldowania adresses for employee "+fname);
    }
}
