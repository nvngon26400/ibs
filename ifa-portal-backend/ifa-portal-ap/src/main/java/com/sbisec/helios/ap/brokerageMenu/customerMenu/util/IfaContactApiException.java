package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

/**
 * この例外は、CCSとの通信でエラーが発生した場合にスローされます。
 *
 * @author lianhua.xia
 * @date 2025-01-22
 */
public class IfaContactApiException extends Exception {

	private static final long serialVersionUID = 1L;

	// メッセージID
	private String messageKey;
	
	public IfaContactApiException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }
	
	public IfaContactApiException(String messageKey, Throwable cause) {
        super(messageKey, cause);
        this.messageKey = messageKey;
    }

	public IfaContactApiException updateKey(String newMessageKey) {
        this.messageKey = newMessageKey;
        return this;
    }

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return messageKey;
	}

}
