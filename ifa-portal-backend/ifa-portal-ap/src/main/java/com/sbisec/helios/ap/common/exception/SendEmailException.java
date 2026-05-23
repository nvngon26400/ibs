package com.sbisec.helios.ap.common.exception;

/**
 * Sending mail is abnormal.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class SendEmailException extends RuntimeException {

    private static final long serialVersionUID = 8073209411200993015L;

    public SendEmailException(String message) {
        super(message);
    }

    public SendEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendEmailException(Throwable cause) {
        super(cause);
    }
}
