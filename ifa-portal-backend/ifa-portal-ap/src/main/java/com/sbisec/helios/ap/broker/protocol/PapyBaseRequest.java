package com.sbisec.helios.ap.broker.protocol;

public interface PapyBaseRequest {
    
    public <T> T getHeader();
    
    public <T> T getParameter();
    
}
