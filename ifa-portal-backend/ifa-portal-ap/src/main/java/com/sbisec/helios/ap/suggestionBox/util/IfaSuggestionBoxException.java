package com.sbisec.helios.ap.suggestionBox.util;

/**
 * Exception handling class of suggestionBox
 * 
 * @author SCSK山岸
 */
public class IfaSuggestionBoxException extends Exception {

    private static final long serialVersionUID = 1L;

    // メッセージID
    private String messageKey;
    // メッセージパラメータ
    private String messageParam;


    public IfaSuggestionBoxException() {
        super();
    }

    public IfaSuggestionBoxException(String messageKey) {
        this.messageKey = messageKey;
    }

    public IfaSuggestionBoxException(String messageKey, String messageParam) {
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
