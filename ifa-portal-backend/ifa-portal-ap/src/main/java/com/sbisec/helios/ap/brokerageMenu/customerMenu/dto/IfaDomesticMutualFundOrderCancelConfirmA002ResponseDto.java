package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文取消確認 A002 レスポンスパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 金額（数値(整数)）. */
    private String amount;
    
    /** 口数（数値(整数)）. */
    private String unit;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 利用ポイント（数値(整数)）. */
    private String point;
    
    /** ポイント種別（全角半角）. */
    private String pointType;
    
    /** 受付経路区分. */
    private String callcenterClassification;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNumber;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNumber;
    
    /** 購入解約方法. */
    private String buyCancelMethod;
}
