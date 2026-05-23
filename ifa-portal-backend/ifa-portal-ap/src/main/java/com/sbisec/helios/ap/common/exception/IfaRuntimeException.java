package com.sbisec.helios.ap.common.exception;

import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Getter;

/**
 * IFA実行時想定外例外<br/>
 * アプリのバグ等でしか発生しないエラーが発生した際にthrowするための実行時例外
 *
 * @author 河口
 *
 */
public class IfaRuntimeException extends RuntimeException {
    
    /*　Serial Version UID */
    private static final long serialVersionUID = 1L;
    
    /*　メッセージID */
    @Getter
    private String messageId;
    
    /*　メッセージパラメータ */
    @Getter
    private String[] messageParams;
    
    /**
     * コンストラクタ
     *
     * @param messageId メッセージID
     */
    public IfaRuntimeException(String messageId) {
        
        this(messageId, null, null);
    }
    
    /**
     * コンストラクタ
     *
     * @param messageId メッセージID
     * @param messageParams メッセージパラメータ
     */
    public IfaRuntimeException(String messageId, String[] messageParams) {
        
        this(messageId, messageParams, null);
    }
    
    /**
     * コンストラクタ
     *
     * @param messageId メッセージID
     * @param cause 発生元例外
     */
    public IfaRuntimeException(String messageId, Throwable cause) {
        
        this(messageId, null, cause);
    }
    
    /**
     * コンストラクタ
     *
     * @param messageId メッセージID
     * @param messageParams メッセージパラメータ
     * @param cause 発生元例外
     */
    public IfaRuntimeException(String messageId, String[] messageParams, Throwable cause) {
        
        // 例外のメッセージに、メッセージIDとパラメータを元に変換したメッセージを設定する
        super(messageId + ": " + IfaCommonUtil.getMessage(messageId, messageParams), cause);
        
        // メッセージID、メッセージパラメータは個別に取得可能とするためインスタンス変数に設定する
        // メッセージ本文は、親クラスで定義されたgetMessage()メソッドで取得可能
        this.messageId = messageId;
        this.messageParams = messageParams;
    }
    
    /**
     * メッセージIDが付与されていないメッセージ本文を取得する<br />
     * 通常のgetMessage()では下記の形式のメッセージが取得されるがメッセージのみ取得したい場合にこちらのメソッドで取得する<br />
     * ・メッセージID: メッセージ
     *
     * @return メッセージIDが付与されていないメッセージ
     */
    public String getMessageWithoutMessageId() {
        
        return IfaCommonUtil.getMessage(messageId, messageParams);
        
    }
}
