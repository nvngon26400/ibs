package com.sbisec.helios.ap.ccsApi.exception;

/**
 * CcsApi用の基底の例外クラス
 */
public class CcsApiException extends Exception {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public CcsApiException() {
        super();
    }

    /**
     * コンストラクタ
     * @param x_msg 詳細メッセージ
     */
    public CcsApiException(final String x_msg) {
        super(x_msg);
    }

    /**
     * コンストラクタ
     * @param x_msg 詳細メッセージ
     * @param x_cau 原因
     */
    public CcsApiException(final String x_msg, final Throwable x_cau) {
        super(x_msg, x_cau);
    }

    /**
     * コンストラクタ
     * @param x_cau 原因
     */
    public CcsApiException(final Throwable x_cau) {
        super(x_cau);
    }
}
