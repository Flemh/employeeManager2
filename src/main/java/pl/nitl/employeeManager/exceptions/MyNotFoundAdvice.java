package pl.nitl.employeeManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String MyNotFoundHandler(MyNotFoundException ex) {
        return ex.getMessage();
    }
}
