package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 代用有価証券振替情報 Dto.

 * @author SCSK 川崎
   @date 03/26/2024
 */
@Data
public class CollateralSecuritiesTransfer implements Serializable {
    
    private static final long serialVersionUID = -2609274086678460686L;
    
    public CollateralSecuritiesTransfer() {
        
    }
    
    /** 銘柄情報 */
    private Securities securities;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 保護区分 */
    private String depositType;
    
    /** 保有株数 */
    private String securitiesQuantity;
    
    /** 受渡未到来株数 */
    private String unPaymentQuantity;
    
    /** 評価単価 */
    private String evaluationPrice;
    
    /** 代用掛目 */
    private String assessmentRate;
    
    /** 代用有価証券評価額 */
    private String evaluationAmount;
    
}
