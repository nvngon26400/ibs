package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListA002DtoRequest {
    
    /** 商品区分（全角半角）. */
    private String securityType;
    
    /** EC受注番号（国内株式注文）. */
    private String ecOrderNo;
    
    /** 発注区分（国内株式注文）. */
    private String hattyuuKbn;
    
    /** EC受注番号（国内投信注文）. */
    private String domesticMutualFundOrderStatusEcOrderNo;
    
    /** 銘柄コード（募集注文）. */
    private String brandCode;
    
    /** 預り区分（募集注文）. */
    private String depositType;
    
    /** 注文番号（外株委託注文）. */
    private String orderNumber;
    
    /** 注文SUB番号（外株委託注文）. */
    private String orderSubNumber;
    
    /** 管理番号（店頭取引注文）. */
    private String manageNumber;

    /** 商品区分（国内投信）. */
    private String orderType;
    
}
