package dariomorgrane.RCSLabs.web;

import dariomorgrane.RCSLabs.exception.WebLayerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PivotTableAdvice {

    @ResponseBody
    @ExceptionHandler(WebLayerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String WebLayerExceptionHandler(WebLayerException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String MissingRequestParameterExceptionHandler(MissingServletRequestParameterException exception) {
        return exception.getMessage();
    }

}
