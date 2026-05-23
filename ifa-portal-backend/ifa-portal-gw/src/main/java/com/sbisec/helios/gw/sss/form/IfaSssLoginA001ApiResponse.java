package com.sbisec.helios.gw.sss.form;

import lombok.Data;

@Data
public class IfaSssLoginA001ApiResponse {

    /** エラーフラグ */
    private String errorFlg;

    /** エラーコード */
    private String errorCode;

    /** エラーメッセージ */
    private String errorMessage;

    /** SSSシステムURL */
    private String sssLoginUrl;

    /** 認証トーケン */
    private String sssLoginParam;

}
