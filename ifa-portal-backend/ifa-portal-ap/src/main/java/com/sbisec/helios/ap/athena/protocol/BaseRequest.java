package com.sbisec.helios.ap.athena.protocol;

public interface BaseRequest {
    
    public <T> T getHeader();
    
    public <T> T getParameter();
    
}