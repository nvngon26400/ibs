package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * DTOクラス 積立設定変更受付機能OUT Dto
 */
public class FundReserveSettingChangeReceptApiOut extends DtoOut {

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** 積立設定変更前データ */
    private FundReserveSettingBaseOut reserveSettingChangeBefore;

    /** 積立設定変更後データ */
    private FundReserveSettingBaseOut reserveSettingChangeAfter;

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

    public FundReserveSettingBaseOut getReserveSettingChangeBefore() {
        return reserveSettingChangeBefore;
    }

    public void setReserveSettingChangeBefore(FundReserveSettingBaseOut reserveSettingChangeBefore) {
        this.reserveSettingChangeBefore = reserveSettingChangeBefore;
    }

    public FundReserveSettingBaseOut getReserveSettingChangeAfter() {
        return reserveSettingChangeAfter;
    }

    public void setReserveSettingChangeAfter(FundReserveSettingBaseOut reserveSettingChangeAfter) {
        this.reserveSettingChangeAfter = reserveSettingChangeAfter;
    }

}
