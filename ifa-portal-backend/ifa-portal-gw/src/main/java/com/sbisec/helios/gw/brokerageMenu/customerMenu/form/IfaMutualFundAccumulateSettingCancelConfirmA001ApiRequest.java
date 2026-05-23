package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 投信積立設定解除確認 A001 リクエストパラメータ
 *
 * @author WJL
 *
 *     2025/04/11 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA001ApiRequest {
	
    /** 解除対象積立設定リスト. */
	@NotNull(message = "解除対象積立設定リスト")
    private List<CancelTargetAccumulateSettingDetail> cancelTargetAccumulateSettingList; 
  
}
