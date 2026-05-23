package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeA002SbiRapManageFeeDisplayControlResponseDto {

    /** アイテム名 */
    private String itemName;

    /** サービス名 */
    private String serviceName;

    /** 表示制御フラグ */
    private String displayControlFlag;

}
