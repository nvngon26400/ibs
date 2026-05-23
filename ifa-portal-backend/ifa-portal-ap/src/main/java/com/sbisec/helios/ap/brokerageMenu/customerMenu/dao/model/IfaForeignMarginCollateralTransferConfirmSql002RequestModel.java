package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 米株信用代用振替確認 SQL002 リクエストモデル
 *
 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmSql002RequestModel {
    
    /** IFA代用振替指示番号 */
    private String stockTransferNo;
    
    /** IFA代用振替指示サブ番号. */
    private String stockTransferSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 国コード. */
    private String countryCode;
    
    /** ティッカーコード. */
    private String brandCode;
    
    /** 預り区分. */
    private String depositType;
    
    /** 代用振替区分. */
    private String stockTransferType;
    
    /** 振替数量. */
    private String transferQuantity;
    
    /** 作成者. */
    private String createUser;

    /** 更新者. */
    private String updateUser;

}
