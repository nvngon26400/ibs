package com.sbisec.helios.ap.safe.service.common.enums;

/**
 * Serviceのエラーコード
 */
public enum ServiceErrorCode {
    /** 無効なパラメータ */
    INVALID_PARAMETER,
    /** RestClientによる通信エラー */
    HTTP_ERROR,
    /** その他エラー */
    SYSTEM_ERROR,
    ;
}
