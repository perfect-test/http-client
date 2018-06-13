package com.perfecttest.http.exception;

/**
 * Created by Sidelnikov Mikhail on 09.07.15.
 * Exception for throw when some errors occured while sending request
 */
public class SendingRequestException extends RuntimeException {
    public SendingRequestException(String message) {
        super(message);
    }

    public SendingRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
