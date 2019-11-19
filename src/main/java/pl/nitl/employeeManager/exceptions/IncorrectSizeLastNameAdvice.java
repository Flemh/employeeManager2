package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectSizeLastNameAdvice {
    @ResponseBody
    @ExceptionHandler(IncorrectSizeLastNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IncorrectSizeLastNameHandler(IncorrectSizeLastNameException ex) {
        return ex.getMessage();
    }
}
