package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import lombok.Data;

/**
 * 印影照会 初期化
 *
 * @author SCSK
 * 
 */
@Data
public class IfaImprintInquiryA001ResponseDto {

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 顧客名（漢字）（全角半角） */
    private String customerNameKanji;

    /** 顧客名（カナ）（全角半角） */
    private String customerNameKana;

    /** 帳票コード */
    private String ledgerCode;

    /** 帳票名 */
    private String ledgerName;

    /** 受入基準日 */
    private String acceptStandardDate;

    /** 帳票ファイル */
    private String ledgerFile;

    /** エラーコード（半角英数字） */
    private String code;

    /** エラーメッセージ（全角半角） */
    private String errMessage;

    /** 帳票ファイルの幅 */
    private String width;

    /** 帳票ファイルの高さ */
    private String height;

}
