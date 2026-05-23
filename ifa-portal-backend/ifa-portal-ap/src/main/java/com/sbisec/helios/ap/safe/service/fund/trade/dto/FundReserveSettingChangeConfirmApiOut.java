package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * DTOクラス 積立設定変更確認機能OUT Dto
 */
public class FundReserveSettingChangeConfirmApiOut extends DtoOut {

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

    /**
     * 積立設定変更前データを取得する。
     * @return 積立設定変更前データ
     */
    public FundReserveSettingBaseOut getReserveSettingChangeBefore() {
        return reserveSettingChangeBefore;
    }

    /**
     * 積立設定変更前データを設定する。
     * @param reserveSettingChangeBefore 積立設定変更前データ
     */
    public void setReserveSettingChangeBefore(FundReserveSettingBaseOut reserveSettingChangeBefore) {
        this.reserveSettingChangeBefore = reserveSettingChangeBefore;
    }

    /**
     * 積立設定変更後データを取得する。
     * @return 積立設定変更後データ
     */
    public FundReserveSettingBaseOut getReserveSettingChangeAfter() {
        return reserveSettingChangeAfter;
    }

    /**
     * 積立設定変更後データを設定する。
     * @param reserveSettingChangeAfter 積立設定変更後データ
     */
    public void setReserveSettingChangeAfter(FundReserveSettingBaseOut reserveSettingChangeAfter) {
        this.reserveSettingChangeAfter = reserveSettingChangeAfter;
    }

}
