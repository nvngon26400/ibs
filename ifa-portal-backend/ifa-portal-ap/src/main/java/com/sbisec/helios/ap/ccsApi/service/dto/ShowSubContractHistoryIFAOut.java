package com.sbisec.helios.ap.ccsApi.service.dto;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴参照APIパラメータ設定(Out)
 */
@Data
public class ShowSubContractHistoryIFAOut implements CcsApiCommOutIF {

    /** 顧客ID: バイト数 */
    public final int ACCOUNT_ID_LEN = 9;
    /** エラーコード: バイト数 */
    public final int ERROR_CODE_LEN = 6;
    /** エラーメッセージ: バイト数 */
    public final int ERROR_MESSAGE_LEN = 40;
    /** 該当履歴件数トータル: バイト数 */
    public final int TOTAL_NUMBER_LEN = 3;
    /** 大分類: バイト数 */
    public final int BIG_CLASS_LEN = 20;
    /** 中分類: バイト数 */
    public final int MID_CLASS_LEN = 210;
    /** 記録日時: バイト数 */
    public final int RECORD_DATE_LEN = 20;
    /** ステータス: バイト数 */
    public final int ORDER_STATUS_LEN = 20;
    /** 内容: バイト数 */
    public final int CONTENT_LEN = 800;
    /** 担当者名: バイト数 */
    public final int USER_MEI_LEN = 40;
    /** 枝番: バイト数 */
    public final int EDABAN_LEN = 5;
    /** 詳細区分: バイト数 */
    public final int SHOUSAI_KBN_LEN = 1;

    /** 接触履歴リストのバイト数 大分類 + 中分類 + ステータス + 内容 + 担当者名 + 枝番 + 詳細区分 */
    public final int LIST_LENS = 
              BIG_CLASS_LEN
            + MID_CLASS_LEN
            + RECORD_DATE_LEN
            + ORDER_STATUS_LEN
            + CONTENT_LEN
            + USER_MEI_LEN
            + EDABAN_LEN
            + SHOUSAI_KBN_LEN;
    
    /** 顧客ID */
    private String AccountId;

    /** エラーコード */
    private String ErrorCode;

    /** エラーメッセージ */
    private String ErrorMessage;

    /** 該当詳細件数トータル */
    private String TotalNumber;

    /** CCS接触履歴リスト */
    private List<Contact> contactList;

    @Data
    public static class Contact {
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
        /** 担当者名 */
        private String UserMei;
        /** 枝番 */
        private String Edaban;
        /** 詳細区分 */
        private String ShousaiKbn;
    }
}
