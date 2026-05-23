package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 次回発注予定
 */
public class NextOrderPlan {

    /** 発注予定日 */
    private String orderPlanDate;

    /** 発注予定金額 */
    private BigDecimal orderPlanAmount;

    /** 予定件数 */
    private BigDecimal orderCount;

    /**
     * 発注予定日を取得する。
     * @return 発注予定日
     */
    public String getOrderPlanDate() {
        return orderPlanDate;
    }

    /**
     * 発注予定日を設定する。
     * @param orderPlanDate 発注予定日
     */
    public void setOrderPlanDate(String orderPlanDate) {
        this.orderPlanDate = orderPlanDate;
    }

    /**
     * 発注予定金額を取得する。
     * @return 発注予定金額
     */
    public BigDecimal getOrderPlanAmount() {
        return orderPlanAmount;
    }

    /**
     * 発注予定金額を設定する。
     * @param orderPlanAmount 発注予定金額
     */
    public void setOrderPlanAmount(BigDecimal orderPlanAmount) {
        this.orderPlanAmount = orderPlanAmount;
    }

    /**
     * 予定件数を取得する。
     * @return 予定件数
     */
    public BigDecimal getOrderCount() {
        return orderCount;
    }

    /**
     * 予定件数を設定する。
     * @param orderCount 予定件数
     */
    public void setOrderCount(BigDecimal orderCount) {
        this.orderCount = orderCount;
    }
}
