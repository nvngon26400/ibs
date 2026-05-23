package com.sbisec.helios.ap.pom.utils;

/**
 * Exception handling class of POM
 *
 * @author SCSK
 *
 */
public class PomException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public PomException(Exception e) {
        
        super(e);
    }
    
    public PomException(String msg) {
        
        super(msg);
    }
    
}
