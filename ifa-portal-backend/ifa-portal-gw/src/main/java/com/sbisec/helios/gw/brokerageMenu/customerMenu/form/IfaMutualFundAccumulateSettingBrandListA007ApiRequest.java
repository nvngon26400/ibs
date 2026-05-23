package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeTargetDetail;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 A007 リクエストパラメータ
 *
 * @author nicksen.li
 *
 * 2025/07/21 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListA007ApiRequest {

    /** 変更対象積立設定リスト. */
    @NotNull(message = "変更対象積立設定リスト")
    private List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingList;

}
