package com.sbisec.helios.ap.broker.util;

/**
 * Exception handling class of papy
 * 
 * @author katsuhiko.kagoshima
 *
 */
public class PapyConnectionException extends Exception {
    
    private String title;
    
    private String errorMessage;
    
    private String content;
    
    public PapyConnectionException() {
        
        super();
    }
    
    public PapyConnectionException(String msg) {
        
        super(msg);
    }
    
    public PapyConnectionException(String msg, Throwable thro) {
        
        super(msg, thro);
    }
    
    public PapyConnectionException(Throwable thro) {
        
        super(thro);
    }
    
    public String getTitle() {
        
        return title;
    }
    
    public void setTitle(String title) {
        
        this.title = title;
    }
    
    public String getErrorMessage() {
        
        return errorMessage;
    }
    
    public void setErrorMessage(String message) {
        
        this.errorMessage = message;
    }
    
    public String getContent() {
        
        return content;
    }
    
    public void setContent(String content) {
        
        this.content = content;
    }
}
