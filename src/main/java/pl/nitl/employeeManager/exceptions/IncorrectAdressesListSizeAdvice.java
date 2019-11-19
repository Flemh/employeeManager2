package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectAdressesListSizeAdvice {

    @ResponseBody
    @ExceptionHandler(IncorrectAdressesListSizeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IncorrectAdressesListSizeHandler(IncorrectAdressesListSizeException ex) {
        return ex.getMessage();
    }
}
