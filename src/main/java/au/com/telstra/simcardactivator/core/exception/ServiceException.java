package au.com.telstra.simcardactivator.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    @Getter
    private final HttpStatus statusCode;

    @Getter
    private final String errorCode;

    public ServiceException(HttpStatus statusCode, String errorCode, Throwable cause, String message, Object... args){
        super(String.format(message, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
