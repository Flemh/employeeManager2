package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ThereIsNoOneWith5Advice {

    @ResponseBody
    @ExceptionHandler(ThereIsNoOneWith5Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ThereIsNoOneWith5Handler(ThereIsNoOneWith5Exception ex) {
        return ex.getMessage();
    }
}
