package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * ApiInクラス 設定済みの積立一覧(一括変更用)を取得するパラメータ
 */
public class ReserveSettingDataListForBulkUpdateApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public ReserveSettingDataListForBulkUpdateApiIn() {
        super("fund.reserve.get-reserve-setting-for-bulk-update");
    }

    /** 取得対象積立設定リスト */
    List<ReserveSettingDataForBulkUpdate> reserveOrderList;

    public List<ReserveSettingDataForBulkUpdate> getReserveOrderList() {
        return reserveOrderList;
    }

    public void setReserveOrderList(List<ReserveSettingDataForBulkUpdate> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }

}
