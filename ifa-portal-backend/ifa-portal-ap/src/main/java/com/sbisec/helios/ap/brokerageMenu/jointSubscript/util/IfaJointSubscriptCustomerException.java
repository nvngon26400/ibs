package com.sbisec.helios.ap.brokerageMenu.jointSubscript.util;

/**
 * ロールバックエラー
 * 共同募集 顧客管理用
 * 
 * @author 大連 王永宝
 */
public class IfaJointSubscriptCustomerException extends Exception {

    private static final long serialVersionUID = 1L;

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public IfaJointSubscriptCustomerException() {
        super();
    }

    public IfaJointSubscriptCustomerException(String messageId) {
        this.messageId = messageId;
    }
}
