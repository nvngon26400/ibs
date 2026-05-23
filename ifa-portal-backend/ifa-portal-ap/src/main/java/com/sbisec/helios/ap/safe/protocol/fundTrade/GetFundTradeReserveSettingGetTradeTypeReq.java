package com.sbisec.helios.ap.safe.protocol.fundTrade;

import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFundTradeReserveSettingGetTradeTypeReq implements SafeBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private FundReserveCanTradeTypeApiIn parameter = new FundReserveCanTradeTypeApiIn();

    @Data
    @NoArgsConstructor
    public class Header {

        private String token;

    }

    public String getSafeApiUrl() {
        return SafeApiUtil.getSafe_fundTrade_fund_reserve_setting_get_trade_types();
    }

}
