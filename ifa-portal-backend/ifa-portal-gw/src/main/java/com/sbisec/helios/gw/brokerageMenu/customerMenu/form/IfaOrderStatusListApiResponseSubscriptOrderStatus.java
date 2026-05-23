package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

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
public class IfaOrderStatusListApiResponseSubscriptOrderStatus {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 募集受注日時. */
    private String recruitmentOrderDate;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
}
