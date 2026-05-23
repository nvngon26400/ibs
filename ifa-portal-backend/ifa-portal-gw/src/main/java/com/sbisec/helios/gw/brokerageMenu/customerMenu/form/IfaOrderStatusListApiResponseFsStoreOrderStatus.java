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
public class IfaOrderStatusListApiResponseFsStoreOrderStatus {

    /** 管理番号（半角英数字）. */
    private String manageNumber;
    
    /** ステータス. */
    private String status;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文時間. */
    private String orderDayTime;
    
    /** 約定時間. */
    private String tradeDateTime;
    
    /** 注文数量. */
    private String domesticQuantityInput;
    
    /** 取引価格（数値(小数)）. */
    private String tradePrice;
    
    /** ワーニング申請取引（全角半角）. */
    private String warningApplyTrade;
    
    /** 取消理由（全角半角）. */
    private String cancelReason;
    
    /** 取引種別（算出）. */
    private String buySellTypeName;

}
