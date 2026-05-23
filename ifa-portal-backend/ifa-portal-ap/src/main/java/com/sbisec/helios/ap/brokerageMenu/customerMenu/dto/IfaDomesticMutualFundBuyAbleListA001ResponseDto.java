package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 国内投信購入可能一覧 買付可能一覧(NRI_QueryFund) レスポンスパラメタ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaDomesticMutualFundBuyAbleListA001ResponseDto {
    
    /** 媒介可否. */
    // 手動変換
    private String intermediaryValue;
    
    /** 購入可否. */
    // 手動変換
    private String buyAbleValue;
    
    /** 積立可否. */
    // 手動変換
    private String accumulateAbleValue;
    
    /** APIエラーコード. */
    private String apiErrorCode;
    
    /** APIエラーメッセージ. */
    // 手動変換
    private String apiErrorMsg;
    
    /** 乗換優遇限度額.総合口座（今月残）. */
    // 手動変換
    private String transfersPreferentialLimitAmountThisMonth;

    /** 乗換優遇限度額.総合口座（来月残）. */
    // 手動変換
    private String transfersPreferentialLimitAmountNextMonth;

    /** 乗換優遇限度額.ジュニアNISA口座（今月残）. */
    // 手動変換
    private String switchingFavorableTreatmentLimitJuniorNisaAccountThisMonth;

    /** 乗換優遇限度額.ジュニアNISA口座（来月残）. */
    // 手動変換
    private String switchingFavorableTreatmentLimitJuniorNisaAccountNextMonth;
    
    /** 検索結果総数. */
    // 手動変換
    private String hitNumber;
    
    /** 明細リスト. */
    // 手動変換
    private List<IfaDomesticMutualFundBuyAbleListDetail> detailList;
    
}
