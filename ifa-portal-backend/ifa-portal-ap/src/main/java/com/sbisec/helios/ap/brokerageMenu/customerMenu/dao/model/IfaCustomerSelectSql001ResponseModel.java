package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 顧客選択 SQL001 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectSql001ResponseModel {
    
    /** 検索件数. */
    private String searchCount;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 口座開設年月日. */
    private String openAcctDate;
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** お気に入り登録状況（数字）. */
    private String favoRegStatus;
    
    /** 注意情報エラー件数(1件以上の場合1、0件の場合0を返す) */
    private String noticeInfoErrorCount;

    /** 部店コード（IFA取引制限エラー口座.部店コード） */
    private String tradeRestrictButenCode;
    
}
