package com.sbisec.helios.ap.common.dto;

import com.sbisec.helios.ap.common.enums.ErrorLevel;

import lombok.Data;

@Data
public class CheckApiResultDto {

    public CheckApiResultDto(ErrorLevel errorLevel, String code, String message, String convertedMessage) {
        super();
        this.errorLevel = errorLevel;
        this.code = code;
        this.message = message;
        this.convertedMessage = convertedMessage;
    }

    /**
     * 処理結果エラーレベル
     */
    private ErrorLevel errorLevel;
    
    /**
     * メッセージID
     */
    private String messageId;
    

    /**
     * メッセージの適用優先度
     * エラーレベルがWARNINGの場合のみ適用
     */
    private MessagePriority messagePriority;

    /**
     * 処理結果コード
     */
    private String code;
    
    /**
     * 処理結果メッセージ
     */
    private String message;
    
    /**
     * 処理結果メッセージ（変換後）
     */
    private String convertedMessage;
    
    @Override
    public String toString() {
        return "CheckApiResultDto [errorLevel=" + errorLevel + ", messageId=" + messageId + ", code=" + code
            + ", message=" + message + ", convertedMessage=" + convertedMessage + "]";
    }

    public CheckApiResultDto() {
        super();
    }

    public static enum MessagePriority {
        ALWAYS,                     // 最優先で適用される
        ALWAYS_IF_NO_OTHER_ERRORS,  // 他にエラーがなければ適用される
        NEVER                       // 適用されない
    }
  
}
