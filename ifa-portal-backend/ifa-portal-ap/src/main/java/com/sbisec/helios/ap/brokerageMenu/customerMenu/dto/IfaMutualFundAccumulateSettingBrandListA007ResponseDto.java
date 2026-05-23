package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 設定一括変更A007(投信積立設定一括変更入力)のレスポンスパラメタ
 *
 * @author nicksen.li
 * 
 */
@Data
@JsonSerialize
public class IfaMutualFundAccumulateSettingBrandListA007ResponseDto {

    /** 変更対象積立設定リスト. */
    private List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingList;

}
