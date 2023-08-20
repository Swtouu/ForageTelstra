package au.com.telstra.simcardactivator.app.exception;

import au.com.telstra.simcardactivator.core.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class SimCardServiceException extends ServiceException {
    public SimCardServiceException(HttpStatus statusCode, SimCardErrorCode errorCode, Throwable cause, String message, Object... args){
        super(statusCode, errorCode.name(), cause, message, args);
    }

    public SimCardServiceException(HttpStatus statusCode, SimCardErrorCode errorCode, String message, Object... args){
        super(statusCode, errorCode.name(), null, message, args);
    }
}
