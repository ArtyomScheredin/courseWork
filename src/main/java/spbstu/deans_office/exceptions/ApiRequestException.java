package spbstu.deans_office.exceptions;

public class ApiRequestException extends RuntimeException{
    
    public ApiRequestException(String message) {
        super(message);
    }
}
