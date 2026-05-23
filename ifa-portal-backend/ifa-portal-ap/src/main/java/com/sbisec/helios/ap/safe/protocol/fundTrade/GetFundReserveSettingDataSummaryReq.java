package com.sbisec.helios.ap.safe.protocol.fundTrade;

import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFundReserveSettingDataSummaryReq implements SafeBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private ReserveSettingSummaryApiIn parameter = new ReserveSettingSummaryApiIn();

    @Data
    @NoArgsConstructor
    public class Header {

        private String token;

    }

    public String getSafeApiUrl() {
        return SafeApiUtil.getSafe_fundTrade_fund_reserve_setting_data_summary();
    }

}
