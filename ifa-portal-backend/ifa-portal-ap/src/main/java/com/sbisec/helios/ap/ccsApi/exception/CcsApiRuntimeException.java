package com.sbisec.helios.ap.ccsApi.exception;

/**
 * CcsApiの非検査例外クラス
 */
public class CcsApiRuntimeException extends RuntimeException {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public CcsApiRuntimeException() {
        super();
    }

    /**
     * コンストラクタ
     * @param x_msg 詳細メッセージ
     */
    public CcsApiRuntimeException(final String x_msg) {
        super(x_msg);
    }

    /**
     * コンストラクタ
     * @param x_msg 詳細メッセージ
     * @param x_cau 原因
     */
    public CcsApiRuntimeException(final String x_msg, final Throwable x_cau) {
        super(x_msg, x_cau);
    }

    /**
     * コンストラクタ
     * @param x_cau 原因
     */
    public CcsApiRuntimeException(final Throwable x_cau) {
        super(x_cau);
    }
}
