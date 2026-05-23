package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse {
    
    // 前日比5%下落銘柄リスト
    private List<Compare5PercentDeclineBrandList> compare5PercentDeclineBrandList;
    
    /**
     * 前日比5%下落銘柄リスト
     * 
     * @author SCSK
     */
    @Data
    public static class Compare5PercentDeclineBrandList {
        
        // 下落率区分
        private String declineRateKbn;
        
        // ステータス区分
        private String statusClassification;
        
        // 対応方法区分
        private String responseMethodClassification;
        
        // その他内容区分
        private String otherContentsClassification;
        
        // その他詳細
        private String otherDetail;
        
        // 顧客対応日
        private String customerResponseDate;
        
        // 対応完了確認日
        private String responseFinishConfirmDate;
        
        // 部店コード
        private String butenCode;
        
        // 口座番号
        private String accountNumber;
        
        // 契約締結前交付書面コード
        private String customerAttribute;
        
        // 顧客名_姓名(漢字)
        private String customerNameKanji;
        
        // 顧客名_姓名(カナ)
        private String customerNameKana;
        
        // 扱者コード
        private String dealerNumber;
        
        // 仲介業者コード
        private String brokerCode;
        
        // 仲介業者名
        private String brokerName;
        
        // 仲介業支店コード
        private String brokerageBranchCode;
        
        // 仲介業者支店名
        private String brokerBranchName;
        
        // 仲介業者営業員コード
        private String brokerChargeCode;
        
        // 仲介業者担当者名
        private String employeeName;
        
        // 投信協会コード
        private String investmentTrustAssociationCode;
        
        // 投信銘柄名
        private String mutualFundBrandName;
        
        // 基準日（To）
        private String standardDateTo;
        
        // 基準価額（To）
        private String basePriceTo;
        
        // 前日比
        private String diff;
        
        // 下落率
        private String rateOfDecline;
    }
}
