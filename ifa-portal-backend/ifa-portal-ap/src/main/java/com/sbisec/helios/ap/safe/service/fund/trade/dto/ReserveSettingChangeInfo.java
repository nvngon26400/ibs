package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * DTOクラス 積立設定 変更前後情報
 */
public class ReserveSettingChangeInfo {

    /** 積立設定変更前データ */
    private FundReserveSettingBaseOut reserveSettingChangeBefore;

    /** 積立設定変更後データ */
    private FundReserveSettingBaseOut reserveSettingChangeAfter;

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
