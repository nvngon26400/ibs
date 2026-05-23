package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投信積立設定入力SQL002要求
 *
 * @author nicksen.li
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaMutualFundAccumulateSettingInputSql002RequestModel {

    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

}
