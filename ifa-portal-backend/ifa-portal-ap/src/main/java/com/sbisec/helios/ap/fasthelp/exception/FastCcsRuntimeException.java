package com.sbisec.helios.ap.fasthelp.exception;

/**
 * Fasthelpの非検査例外
 */
public class FastCcsRuntimeException extends RuntimeException {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public FastCcsRuntimeException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     */
    public FastCcsRuntimeException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param message 詳細メッセージ
     * @param cause 原因
     */
    public FastCcsRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param cause 原因
     */
    public FastCcsRuntimeException(final Throwable cause) {
        super(cause);
    }
}
