package com.sbisec.helios.ap.fasthelp.exception;

/**
 * Fasthelp用の基底の例外クラス
 */
public class FastCcsException extends Exception {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public FastCcsException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     */
    public FastCcsException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     * @param cause 原因
     */
    public FastCcsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param cause 原因
     */
    public FastCcsException(final Throwable cause) {
        super(cause);
    }
}
