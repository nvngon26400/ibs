package com.sbisec.helios.ap.common.dto;

import lombok.Data;

@Data
public class PossibleCollateralSecuritiesTransfer {

    // 振替指示入力(代用→保護)リスト.銘柄情報
    private String securities;
    
    // 振替指示入力(代用→保護)リスト.市場情報
    private String market;
    
    // 振替指示入力(代用→保護)リスト.代用掛目
    private String assessmentRate;
    
    // 振替指示入力(代用→保護)リスト.保護区分
    private String depositType;
    
    // 振替指示入力(代用→保護)リスト.預り区分
    private String specificAccountCode;
    
    // 振替指示入力(代用→保護)リスト.保有株数
    private String securitiesQuantity;
    
    // 振替指示入力(代用→保護)リスト.受渡未到来株数
    private String unPaymentQuantity;
    
    // 振替指示入力(代用→保護)リスト.評価単価
    private String evaluationPrice;
    
    // 振替指示入力(代用→保護)リスト.代用評価額
    private String evaluationAmount;


}
