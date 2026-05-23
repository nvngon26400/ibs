package com.sbisec.helios.ap.ccsApi.exception;

/**
 * CcsApi用の業務の例外クラス
 */
public class CcsApiBusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    public CcsApiBusinessException() {
        super();
    }

    public CcsApiBusinessException(String x_msg) {
        super(x_msg);
    }

    public CcsApiBusinessException(String x_msg, Throwable x_thr) {
        super(x_msg, x_thr);
    }

    public CcsApiBusinessException(Throwable x_thr) {
        super(x_thr);
    }

    private String g_msg;
    private String g_errCd;

    /**
     * @return the g_msg
     */
    public String getMessage() {
        return g_msg;
    }

    /**
     * @param g_msg the message to set
     */
    public void setMessage(String x_msg) {
        this.g_msg = x_msg;
    }

    /**
     * @return the g_errCd
     */
    public String getErrorCode() {
        return g_errCd;
    }

    /**
     * @param g_errCd the errorCode to set
     */
    public void setErrorCode(String x_errCd) {
        this.g_errCd = x_errCd;
    }

}
