package com.sbisec.helios.ap.fasthelp.protocol;

public interface FastCcsBaseRequest {

    public <T> T getHeader();

    public <T> T getParameter();

}