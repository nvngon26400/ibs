package com.sbisec.helios.ap.safe.common.exception;

/**
 * Safe用の基底の例外クラス
 */
public class SafeException extends Exception {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public SafeException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     */
    public SafeException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     * @param cause 原因
     */
    public SafeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param cause 原因
     */
    public SafeException(final Throwable cause) {
        super(cause);
    }
}
