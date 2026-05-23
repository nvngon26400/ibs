package com.sbisec.helios.ap.safe.service.fund.product.dto;

import java.io.Serializable;

/**
 * Dtoクラス 休日情報
 */
public class HolidayInfoApi implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -7948561478122958413L;

    /** 休場日 */
    private String holiday;

    /** 当日区分 */
    private boolean nextDay;

    /**
     * 休場日を取得する。
     * @return 休場日
     */
    public String getHoliday() {
        return holiday;
    }

    /**
     * 休場日を設定する。
     * @param holiday 休場日
     */
    public void setHoliday(final String holiday) {
        this.holiday = holiday;
    }

    /**
     * 当日区分を取得する。
     * @return 当日区分
     */
    public boolean isNextDay() {
        return nextDay;
    }

    /**
     * 当日区分を設定する。
     * @param nextDay 当日区分
     */
    public void setNextDay(final boolean nextDay) {
        this.nextDay = nextDay;
    }

}
