package com.sbisec.helios.ap.broker.util;

/**
 * Exception handling class of papy
 * 
 * @author katsuhiko.kagoshima
 *
 */
public class PapyValidationException extends Exception {
    
    public PapyValidationException() {
        
        super();
    }
    
    public PapyValidationException(String msg) {
        
        super(msg);
    }
    
    public PapyValidationException(String msg, Throwable thro) {
        
        super(msg, thro);
    }
    
    public PapyValidationException(Throwable thro) {
        
        super(thro);
    }
    
    private String errorMessage;
    
    private String errorCode;
    
    /**
     * @return the message
     */
    public String getErrorMessage() {
        
        return errorMessage;
    }
    
    /**
     * @param message the message to set
     */
    public void setErrorMessage(String message) {
        
        this.errorMessage = message;
    }
    
    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        
        return errorCode;
    }
    
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        
        this.errorCode = errorCode;
    }
}
