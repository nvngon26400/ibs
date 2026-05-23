package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 設定一括変更A007(投信積立設定一括変更入力)
 *
 * @author nicksen.li
 * 
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListA007RequestDto {

    /** 変更対象積立設定リスト. */
    private List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingList;

}
