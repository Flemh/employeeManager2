package pl.nitl.employeeManager.exceptions;

public class IncorrectAdressesListSizeException extends RuntimeException {
    public IncorrectAdressesListSizeException(String fname) {
        super ("Employee's list of adress is incorect for employee "+fname);
    }
}
