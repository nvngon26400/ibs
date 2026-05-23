package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeTargetDetail;

import lombok.Data;

/**
 * 投信積立設定一括変更入力 A001 リクエストパラメータ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeInputA001ApiRequest {

    /** 変更対象積立設定リスト. */
    @NotNull(message = "変更対象積立設定リスト")
    private List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingList;

}
