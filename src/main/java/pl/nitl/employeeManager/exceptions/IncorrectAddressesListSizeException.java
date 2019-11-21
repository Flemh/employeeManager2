package pl.nitl.employeeManager.exceptions;

public class IncorrectAddressesListSizeException extends RuntimeException {

    public IncorrectAddressesListSizeException(String fname) {
        super ("Employee's list of address is incorrect for employee "+fname);
    }
}
