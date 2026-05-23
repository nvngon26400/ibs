package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 代用有価証券振替入力情報 Dto.

 * @author SCSK 川崎
   @date 03/26/2024
 */
@Data
public class CollateralSecuritiesTransferInput implements Serializable {
    
    private static final long serialVersionUID = -4036183909371559620L;
    
    public CollateralSecuritiesTransferInput() {
        
    }
    
    /** 代用有価証券振替区分 */
    private String securitiesTransferType;
    
    /** 振替詳細情報 */
    private List<CollateralSecuritiesTransferDetailInput> transferDetails;
    
}
