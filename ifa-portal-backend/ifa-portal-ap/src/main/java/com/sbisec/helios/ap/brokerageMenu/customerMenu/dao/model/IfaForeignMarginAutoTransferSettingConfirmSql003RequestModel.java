package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel {
    
    /** IFA自動振替設定指示番号. */
    private String autoTransferSettingNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 建余力不足 自動振替設定(米ドル). */
    private String marginBuypowShortfallCash;
    
    /** 建余力不足 自動振替設定(米国株式). */
    private String marginBuypowShortfallSec;
    
    /** 保証金不足 自動振替設定(米ドル). */
    private String marginShortfallCash;
    
    /** 保証金不足 自動振替設定(米国株式). */
    private String marginShortfallSec;
    
    /** 現物買付時 株式自動振替先設定. */
    private String depositType;
    
    /** 設定指示日時. */
    private String configDate;
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private String aPIStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMsg;
    
    /** 作成日時. */
    private String createTime;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
}
