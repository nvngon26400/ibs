package com.sbisec.helios.ap.ccsApi.protocol;

public interface CcsApiBaseReq {

    public <T> T getHeader();

    public <T> T getParameter();

}