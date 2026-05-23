package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 米株信用代用振替入力 SQL001 レスポンスモデル
 *
 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputSql001ResponseModel {
    
    /** 銘柄コード. */
    private String brandCode;

    /** 預り区分. */
    private String depositType;
    
}
