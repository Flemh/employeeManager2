package pl.nitl.employeeManager.exceptions;

public class ThereIsNoOneWith5Exception extends RuntimeException {

    public ThereIsNoOneWith5Exception() {
        super ("There is no one with 5 letters last name");
    }
}
