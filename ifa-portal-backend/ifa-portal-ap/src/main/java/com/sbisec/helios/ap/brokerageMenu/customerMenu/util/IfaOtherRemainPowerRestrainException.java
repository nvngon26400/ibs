package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

/**
 * ロールバックエラー
 * その他余力拘束
 * 
 * @author 大連 王永宝
 */
public class IfaOtherRemainPowerRestrainException extends Exception {

    private static final long serialVersionUID = 1L;

    private String messageId;
    private String apiErrCd;
    private String apiErrMsg;

    public String getMessageId() {
        return messageId;
    }

    public String getApiErrCd() {
        return apiErrCd;
    }

    public String getApiErrMsg() {
        return apiErrMsg;
    }

    public IfaOtherRemainPowerRestrainException() {
        super();
    }

    public IfaOtherRemainPowerRestrainException(String messageId) {
        this.messageId = messageId;
    }
    
    public IfaOtherRemainPowerRestrainException(String apiErrCd, String apiErrMsg) {
        this.apiErrCd = apiErrCd;
        this.apiErrMsg = apiErrMsg;
    }
}
