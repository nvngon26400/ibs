package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import lombok.Data;

@Data
public class IfaForeignPositionDetailX001ResponseDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場略名. */
    private String foreignMarket;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 国内新規約定日. */
    private String domesticTradeDate;
    
    /** 現地決済期日. */
    private String lastTradeDate;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 建玉残数量. */
    private String positionRestQuantity;
    
    /** 新規建単価（外貨）. */
    private String previousDayValue;
    
    /** 新規建代金（外貨）（数値(小数)）. */
    private String foreignNewPositionAmount;
    
    /** 新規建手数料（税込）. */
    private String foreignNewComm;
    
    /** 金利（外貨）. */
    private String interestForeign;
    
    /** 貸株料（外貨）. */
    private String stockLendingPriceForeign;
    
    /** 管理料（外貨）. */
    private String managePriceForeign;
    
    /** 書換料（外貨）. */
    private String transferPriceForeign;
    
    /** 諸経費合計額（外貨）. */
    private String costForeignLink;
    
    /** 一括個別表示フラグ. */
    private String batchIndividualDisplayFlag;
    
}
