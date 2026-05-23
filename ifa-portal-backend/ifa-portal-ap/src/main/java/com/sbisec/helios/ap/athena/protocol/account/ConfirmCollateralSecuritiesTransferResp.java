package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesPower;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginTransferExpectation;

import lombok.Data;

/**
 * 入出金・入出庫サービス - 代用有価証券振替確認API Response.
 *
 * @author SCSK川崎
 * @date: 03/26/2024
 */
@Data
public class ConfirmCollateralSecuritiesTransferResp {
    
    /** 代用有価証券振替情報 */
    private List<CollateralSecuritiesTransfer> transfers;
    
    /** 現在の代用有価証券余力情報  */
    private CollateralSecuritiesPower currentPower;
    
    /** 受付後代用有価証券余力情報  */
    private CollateralSecuritiesPower afterPower;
    
    /** 保証金振替予定情報  */
    private List<MarginTransferExpectation> marginTransferExpectations;
}
