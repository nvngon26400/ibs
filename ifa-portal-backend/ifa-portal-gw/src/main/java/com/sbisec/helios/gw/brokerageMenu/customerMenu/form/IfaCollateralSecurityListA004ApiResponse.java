package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 代用有価証券一覧 A004レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityListA004ApiResponse {
    
    /** 更新日時. */
    private String updateTime;

    /** 表示基準日（受渡日）. */
    private String displayBaseDate;
    
    /** 受渡日（T+0）～受渡日（T+5）リスト. */
    private List<SettlementDate> settlementDateList;
    
    /** 国内株式代用評価金額合計. */
    private String domesticStockCollateralValuationTotal;
                   
    /** 国内投信代用評価金額合計. */
    private String domesticMutualCollateralValuationTotal;
    
    /** 代用有価証券評価額合計（数値(整数)）. */
    private String alternativeSecuritiesTotal;
    
    /** 代用有価証券明細部リスト. */
    private List<Detail> detailList;
    
    /**
     * 受渡日（T+0）～受渡日（T+5）情報
     *
     * @author SCSK
     */
    @Data
    public static class SettlementDate {
        
        /** 受渡日. */
        private String settlementDate;
        
        /** 代用有価証券入庫（数値(整数)）. */
        private String collateralSecurityDeliverIn;
        
        /** 代用有価証券出庫（数値(整数)）. */
        private String collateralSecurityDeliverOut;
        
        /** 代用有価証券評価額合計（数値(整数)）. */
        private String alternativeSecuritiesTotal;
        
        /** 表示基準日. */
        private String displayBaseDate;
    }
    
    /**
     * 代用有価証券明細部
     *
     * @author SCSK
     */
    @Data
    public class Detail {
        
        /** 商品分類（全角半角）. */
        private String securityClass;
        
        /** 銘柄コード（半角英数字）. */
        private String brandCode;
        
        /** 銘柄名（全角半角）. */
        private String brandName;
        
        /** 預り区分（全角半角）. */
        private String depositType;
        
        /** 残高数量（数値(整数)）. */
        private String contPosition;
        
        /** 評価単価. */
        private String valuationPrice;
        
        /** 代用掛目（数値(整数)）. */
        private String collateralAssessment;
        
        /** 代用評価額. */
        private String collateralValuation;
        
        /** 担保貸株区分（全角半角）. */
        private String securityStockLendingClassification;
        
    }
}
