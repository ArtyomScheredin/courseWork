package spbstu.deans_office.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ApiExceptionHandler {

    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
