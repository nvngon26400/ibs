package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * DTOクラス 積立設定解除受付機能のoutDto
 */
public class FundReserveSettingReleaseReceptApiOut extends DtoOut {

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** 解除対象積立設定リスト */
    private List<FundReserveSettingReleaseOut> reserveOrderList;

    /**
     * クレジットカード会社を取得する。
     * @return クレジットカード会社
     */
    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    /**
     * クレジットカード会社を設定する。
     * @param creditCardCompany クレジットカード会社
     */
    public void setCreditCardCompany(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    /**
     * 解除対象積立設定リストを取得する。
     * @return 解除対象積立設定リスト
     */
    public List<FundReserveSettingReleaseOut> getReserveOrderList() {
        return reserveOrderList;
    }

    /**
     * 解除対象積立設定リストを設定する。
     * @param reserveOrderList 解除対象積立設定リスト
     */
    public void setReserveOrderList(List<FundReserveSettingReleaseOut> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }

}
