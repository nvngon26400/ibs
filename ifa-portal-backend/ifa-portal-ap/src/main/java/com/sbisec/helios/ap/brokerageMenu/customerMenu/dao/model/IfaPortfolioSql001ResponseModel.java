package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2024/04/19 新規作成
 *
 * @author SCSK丹波
 */
@Data
public class IfaPortfolioSql001ResponseModel {
    
    /** 証券種別コード */
    private String securityClassCode;
    
    /** 証券種別 */
    private String securityClass;
    
    /** 評価額（数値(小数)） */
    private String valuation;
    
    /** 評価損益 */
    private String profitAndLoss;
    
    /** 仕組債区分 */
    private String structuredBondClassification;
    
}
