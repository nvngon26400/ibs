package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * DTOクラス 積立設定一括変更受付機能OUT Dto
 */
public class FundReserveSettingBulkChangeReceptApiOut extends DtoOut {

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** 積立設定リスト 変更前後情報 */
    List<ReserveSettingChangeInfo> reserveOrderList;

    /** エラー情報リスト */
    private List<ReserveSettingErrorInfo> errorInfos = Lists.newArrayList();

    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    public void setCreditCardCompany(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    public List<ReserveSettingChangeInfo> getReserveOrderList() {
        return reserveOrderList;
    }

    public void setReserveOrderList(List<ReserveSettingChangeInfo> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }

    public List<ReserveSettingErrorInfo> getErrorInfos() {
        return errorInfos;
    }

    public void setErrorInfos(List<ReserveSettingErrorInfo> errorInfos) {
        this.errorInfos = errorInfos;
    }
}
