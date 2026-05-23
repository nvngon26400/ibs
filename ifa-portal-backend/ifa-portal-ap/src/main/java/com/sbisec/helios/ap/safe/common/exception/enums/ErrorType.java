package com.sbisec.helios.ap.safe.common.exception.enums;

/**
 * エラーの区分、ログ出力時のログレベルの判定に使用する
 */
public enum ErrorType {
    /** 業務エラー、ログをWARNレベルで出力する */
    APPLICATION,
    /** システムエラー、ログをERRORレベルで出力する */
    SYSTEM,
    ;
}
