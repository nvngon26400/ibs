package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * ApiOutクラス 設定済みの積立一覧
 *
 */
public class ReserveSettingDataListApiOut extends FundTradeDtoOut {

    /** ヒット件数 */
    private int hitNumber;

    /** 総件数 */
    private int totalCount;

    /** ジュニアNISA口座開設有無 */
    private boolean openedJnisa;

    /** 積立設定リスト */
    private List<ReserveSettingData> reserveOrderList;

    /**
     * ヒット件数を取得する。
     * @return ヒット件数
     */
    public int getHitNumber() {
        return hitNumber;
    }

    /**
     * ヒット件数を設定する。
     * @param hitNumber ヒット件数
     */
    public void setHitNumber(int hitNumber) {
        this.hitNumber = hitNumber;
    }

    /**
     * 総件数を取得する。
     * @return 総件数
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 総件数を設定する。
     * @param totalCount 総件数
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * ジュニアNISA口座開設有無を取得する。
     * @return ジュニアNISA口座開設有無
     */
    public boolean isOpenedJnisa() {
        return openedJnisa;
    }

    /**
     * ジュニアNISA口座開設有無を設定する。
     * @param openedJnisa ジュニアNISA口座開設有無
     */
    public void setOpenedJnisa(boolean openedJnisa) {
        this.openedJnisa = openedJnisa;
    }

    /**
     * 積立設定リストを取得する。
     * @return 積立設定リスト
     */
    public List<ReserveSettingData> getReserveOrderList() {
        return reserveOrderList;
    }

    /**
     * 積立設定リストを設定する。
     * @param reserveOrderList 積立設定リスト
     */
    public void setReserveOrderList(List<ReserveSettingData> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }
}
