package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaLevelFeeA002ApiResponse {

    /** 残高連動手数料リスト */
    private List<IfaLevelFeeA002LevelFeeApiResponse> levelFeeList;

}
