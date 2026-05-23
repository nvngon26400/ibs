package com.sbisec.helios.ap.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 *代用有価証券振替可能一覧取得APIOutDto
 *
 * @author SCSK
 */

@Data
public class ListPossibleCollateralSecuritiesTransfersOutData {

    // 振替指示入力(代用→保護)リスト
    public List<PossibleCollateralSecuritiesTransfer> collateralTransfers = new ArrayList<PossibleCollateralSecuritiesTransfer>();

    // エラーコード
    public String errorCode;

}
