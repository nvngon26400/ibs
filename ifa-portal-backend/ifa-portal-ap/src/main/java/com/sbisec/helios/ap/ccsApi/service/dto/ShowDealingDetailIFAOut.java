package com.sbisec.helios.ap.ccsApi.service.dto;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴詳細参照APIパラメータ設定(Out)
 */
@Data
public class ShowDealingDetailIFAOut implements CcsApiCommOutIF{

    /** 顧客ID: バイト数 */
    public final int ACCOUNT_ID_LEN = 9;
    /** エラーコード: バイト数 */
    public final int ERROR_CODE_LEN = 6;
    /** エラーメッセージ: バイト数 */
    public final int ERROR_MESSAGE_LEN = 40;
    /** 該当詳細件数トータル: バイト数 */
    public final int TOTAL_NUMBER_LEN = 3;
    /** 受付シート: バイト数 */
    public final int UKETSUKESHEET_LEN = 10;
    /** 大分類: バイト数 */
    public final int BIG_CLASS_LEN = 20;
    /** 中分類: バイト数 */
    public final int MID_CLASS_LEN = 20;
    /** 記録日時: バイト数 */
    public final int RECORD_DATE_LEN = 20;
    /** ステータス: バイト数 */
    public final int ORDER_STATUS_LEN = 20;
    /** 内容: バイト数 */
    public final int CONTENT_LEN = 500;

    /** 接触履歴リストのバイト数 受付シート + 大分類 + 中分類 + ステータス + 内容 */
    public final int LIST_LENS = 
              UKETSUKESHEET_LEN
            + BIG_CLASS_LEN
            + MID_CLASS_LEN
            + RECORD_DATE_LEN
            + ORDER_STATUS_LEN
            + CONTENT_LEN;
    
    /** 顧客ID */
    private String AccountId;

    /** エラーコード */
    private String ErrorCode;

    /** エラーメッセージ */
    private String ErrorMessage;

    /** 該当詳細件数トータル */
    private String TotalNumber;

    /** CCS接触履歴受付詳細リスト */
    private List<ContactDetail> contactDetailList;

    @Data
    public static class ContactDetail {
        /** 受付シート */
        private String Uketsukesheet;
        /** 大分類 */
        private String BigClass;
        /** 中分類 */
        private String MidClass;
        /** 記録日時 */
        private String RecordDate;
        /** ステータス */
        private String OrderStatus;
        /** 内容 */
        private String Content;
    }
}
