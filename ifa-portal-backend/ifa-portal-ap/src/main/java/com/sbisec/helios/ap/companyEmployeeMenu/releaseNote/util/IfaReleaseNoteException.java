package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util;

/**
 * SUB0512-02 リリースノートException
 *
 * @author SBI大連 夏
 * @date 2025/11/06
 */
public class IfaReleaseNoteException extends Exception {

    private static final long serialVersionUID = 1L;

    // メッセージID
    private String messageKey;
    // メッセージパラメータ
    private String messageParam;

    public IfaReleaseNoteException() {
        super();
    }

    public IfaReleaseNoteException(String messageKey) {
        this.messageKey = messageKey;
    }

    public IfaReleaseNoteException(String messageKey, String messageParam) {
        this.messageKey = messageKey;
        this.messageParam = messageParam;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageParam() {
        return messageParam;
    }

    public void setMessageParam(String messageParam) {
        this.messageParam = messageParam;
    }

}
