package com.sbisec.helios.ap.safe.protocol;

public interface SafeBaseRequest {

    public <T> T getHeader();

    public <T> T getParameter();

}