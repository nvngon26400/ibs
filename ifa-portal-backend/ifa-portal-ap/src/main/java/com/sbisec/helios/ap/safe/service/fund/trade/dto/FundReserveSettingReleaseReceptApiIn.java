package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * DTOクラス 積立設定解除機能のinDto
 */
public class FundReserveSettingReleaseReceptApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public FundReserveSettingReleaseReceptApiIn() {
        super("fund.reserve.setting.release.recept");
    }

    /** 解除対象積立設定リスト */
    List<FundReserveSettingReleaseIn> reserveOrderList;

    /** 受付経路区分 */
    private String routeType;

    /** オペレーターID */
    private String operatorId;

    /**
     * 解除対象積立設定リストを取得する。
     * @return 解除対象積立設定リスト
     */
    public List<FundReserveSettingReleaseIn> getReserveOrderList() {
        return reserveOrderList;
    }

    /**
     * 解除対象積立設定リストを設定する。
     * @param reserveOrderList 解除対象積立設定リスト
     */
    public void setReserveOrderList(List<FundReserveSettingReleaseIn> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }

    /**
     * 受付経路区分を取得する。
     * @return 受付経路区分
     */
    public String getRouteType() {
        return routeType;
    }

    /**
     * 受付経路区分を設定する。
     * @param routeType 受付経路区分
     */
    public void setRouteType(final String routeType) {
        this.routeType = routeType;
    }

    /**
     * オペレーターIDを取得する。
     * @return オペレーターID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * オペレーターIDを設定する。
     * @param operatorId オペレーターID
     */
    public void setOperatorId(final String operatorId) {
        this.operatorId = operatorId;
    }
}
