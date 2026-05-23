package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleCollateralSecuritiesTransfer;

/**
 * 代用有価証券振替可能一覧取得API Response
 *
 * 2023/11/22移植
 */
public class ListPossibleCollateralSecuritiesTransfersResp {
    
    public ListPossibleCollateralSecuritiesTransfersResp() {
    
    }
    
    private List<PossibleCollateralSecuritiesTransfer> protectionTransfers; // 振替指示入力(保護→代用)
    
    private List<PossibleCollateralSecuritiesTransfer> collateralTransfers; // 振替指示入力(代用→保護)
    
    /**
     * @return 振替指示入力(保護→代用)
     */
    public List<PossibleCollateralSecuritiesTransfer> getProtectionTransfers() {
        
        return protectionTransfers;
    }
    
    /**
     * @param protectionTransfers 振替指示入力(保護→代用)
     */
    public void setProtectionTransfers(List<PossibleCollateralSecuritiesTransfer> protectionTransfers) {
        
        this.protectionTransfers = protectionTransfers;
    }
    
    /**
     * @return 振替指示入力(代用→保護)
     */
    public List<PossibleCollateralSecuritiesTransfer> getCollateralTransfers() {
        
        return collateralTransfers;
    }
    
    /**
     * @param collateralTransfers 振替指示入力(代用→保護)
     */
    public void setCollateralTransfers(List<PossibleCollateralSecuritiesTransfer> collateralTransfers) {
        
        this.collateralTransfers = collateralTransfers;
    }
    
}
