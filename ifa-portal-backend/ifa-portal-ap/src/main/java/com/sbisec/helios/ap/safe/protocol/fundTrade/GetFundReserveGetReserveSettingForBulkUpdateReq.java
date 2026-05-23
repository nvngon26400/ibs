package com.sbisec.helios.ap.safe.protocol.fundTrade;

import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListForBulkUpdateApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFundReserveGetReserveSettingForBulkUpdateReq implements SafeBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private ReserveSettingDataListForBulkUpdateApiIn parameter = new ReserveSettingDataListForBulkUpdateApiIn();

    @Data
    @NoArgsConstructor
    public class Header {

        private String token;

    }

    public String getSafeApiUrl() {
        return SafeApiUtil.postSafe_fundTrade_fund_reserve_get_reserve_setting_for_bulk_update();
    }

}