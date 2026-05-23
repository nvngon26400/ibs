package com.sbisec.helios.ap.common.exception;

public class FowardException extends RuntimeException {

    public FowardException(Throwable cause) {
        super("FowardException occured.", cause);
    }

    public FowardException(String message) {
        super(message);
    }

    public FowardException(String message, Throwable cause) {
        super(message, cause);
    }
}
