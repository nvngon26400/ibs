package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * ApiInクラス 設定済みの積立金額サマリーを取得するパラメータ
 *
 */
public class ReserveSettingSummaryApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public ReserveSettingSummaryApiIn() {
        super("fund.reserve.setting_data_summary");
    }

}
