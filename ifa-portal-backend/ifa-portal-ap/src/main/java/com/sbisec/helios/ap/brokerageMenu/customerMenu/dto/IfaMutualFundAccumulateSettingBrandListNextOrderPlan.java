package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 次回発注予定 レスポンスパラメタ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListNextOrderPlan {
//
//    /** 発注予定件数 */
//    private String orderCount;

    /** 発注予定日 */
    private String orderPlanDate;

    /** 発注予定金額 */
    private String orderPlanAmount;

}
