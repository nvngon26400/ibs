package com.sbisec.helios.gw.sss.form;

import lombok.Data;

@Data
public class IfaSssLoginA001ApiRequest {

    /** SSSメニュー： 0:SSS(債券メニュー) 1:SSS(新債券メニュー)*/
    private String sssType = "0";

}
