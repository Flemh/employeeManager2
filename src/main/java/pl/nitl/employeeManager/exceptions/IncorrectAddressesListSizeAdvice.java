package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectAddressesListSizeAdvice {

    @ResponseBody
    @ExceptionHandler(IncorrectAddressesListSizeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IncorrectAddressesListSizeHandler(IncorrectAddressesListSizeException ex) {
        return ex.getMessage();
    }
}
