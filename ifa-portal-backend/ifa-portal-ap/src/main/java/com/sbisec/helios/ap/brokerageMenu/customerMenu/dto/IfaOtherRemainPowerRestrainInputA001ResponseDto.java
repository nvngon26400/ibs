package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * その他余力拘束注文入力 A001レスポンスDto
 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputA001ResponseDto {

    /** 買付余力照会.買付余力 */
    private String buyingPowerTotal;
    
    /** 買付余力照会.買付余力(JrNISA) */
    private String buyingPowerTotalJrnisa;
    
    /** 買付余力照会.総合NISA(成長投資枠）買付可能枠(当年) */
    private String isaSeityoBuyLimit;
    
    /** 買付余力照会.総合NISA(つみたて）買付可能枠(当年) */
    private String isaTsumitateBuyLimit;
    
    /** 口座 */
    private String accountType;
    
    /** 拘束種別 */
    private String restrainType;
    
    /** 拘束期限 */
    private String restrainDateTo;
    
    /** 拘束理由 */
    private String restrainReason;
    
    /** 確認項目 */
    private String confirmItem;
    
    /** 拘束金額（買付余力） */
    private String netAmount;
    
    /** 拘束金額（NISA成長投資枠） */
    private String isaSeityoLimitAmount;
    
    /** 拘束金額（NISAつみたて投資枠） */
    private String isaTsumitateLimitAmount;
    
    /** ジュニアNISA口座フラグ */
    private String jrNisageneralAccountFlag;
    
    /** 注文リスト. */
    private List<IfaOtherRemainPowerRestrainInputOrderData> orderData;

}
