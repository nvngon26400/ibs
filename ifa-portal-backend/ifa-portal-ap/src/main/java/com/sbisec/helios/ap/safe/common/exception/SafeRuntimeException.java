package com.sbisec.helios.ap.safe.common.exception;

/**
 * Safeの非検査例外
 */
public class SafeRuntimeException extends RuntimeException {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public SafeRuntimeException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     */
    public SafeRuntimeException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     * @param cause 原因
     */
    public SafeRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param cause 原因
     */
    public SafeRuntimeException(final Throwable cause) {
        super(cause);
    }
}
