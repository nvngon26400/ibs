package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * DTOクラス 積立設定一括変更確認機能IN Dto
 */
public class FundReserveSettingBulkChangeConfirmApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public FundReserveSettingBulkChangeConfirmApiIn() {
        super("fund.reserve.setting.bulk.change.confirm");
    }

    /** 変更対象積立設定リスト */
    private List<ChangeReserveSettingInfo> reserveOrderList;

    /** 受付経路区分 */
    private String routeType;

    /** オペレーターID */
    private String operatorId;

    public List<ChangeReserveSettingInfo> getReserveOrderList() {
        return reserveOrderList;
    }

    public void setReserveOrderList(List<ChangeReserveSettingInfo> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

}
