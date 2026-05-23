package com.sbisec.helios.ap.common.exception;

public class LogRecordingException extends RuntimeException {

    private static final long serialVersionUID = -6979310755185472195L;

    public LogRecordingException(String message) {
        super(message);
    }

    public LogRecordingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogRecordingException(Throwable cause) {
        super(cause);
    }
}
