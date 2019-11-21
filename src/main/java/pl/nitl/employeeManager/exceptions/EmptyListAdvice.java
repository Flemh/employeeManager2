package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmptyListAdvice {

        @ResponseBody
        @ExceptionHandler(EmptyListException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String emptyListExceptionHandler(EmptyListException ex) {
            return ex.getMessage();
        }
}
