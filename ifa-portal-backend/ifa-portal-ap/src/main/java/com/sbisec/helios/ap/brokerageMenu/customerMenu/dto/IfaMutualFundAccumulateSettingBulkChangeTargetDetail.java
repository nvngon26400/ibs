package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧(設定一括変更) 明細
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeTargetDetail {

    /** ファンドコード（回数）. */
    private String mfgo;

    /** ファンドコード（号）. */
    private String mfkaisu;

    /** 協会コード. */
    private String fundCode;

    /** 預り区分. */
    private String accountType;

    /** 決済方法. */
    private String paymentMethod;

}
