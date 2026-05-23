package com.sbisec.helios.ap.athena.protocol.account.dto;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;
import lombok.Data;

@Data
public class PossibleCollateralSecuritiesTransfer {
    
    /** 銘柄情報 */
    private Securities securities;
    
    /** 市場情報 */
    private Market market;
    
    /** 代用掛目 */
    private String assessmentRate;
    
    /** 保護区分 */
    private String depositType;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 保有株数 */
    private String securitiesQuantity;
    
    /** 受渡未到来株数 */
    private String unPaymentQuantity;
    
    /** 評価単価 */
    private String evaluationPrice;
    
    /** 代用評価額 */
    private String evaluationAmount;
    
}
