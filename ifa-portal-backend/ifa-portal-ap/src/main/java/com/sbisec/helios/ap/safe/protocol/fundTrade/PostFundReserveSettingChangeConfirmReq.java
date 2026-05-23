package com.sbisec.helios.ap.safe.protocol.fundTrade;

import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeConfirmApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostFundReserveSettingChangeConfirmReq implements SafeBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private FundReserveSettingChangeConfirmApiIn parameter = new FundReserveSettingChangeConfirmApiIn();

    @Data
    @NoArgsConstructor
    public class Header {

        private String token;

    }

    public String getSafeApiUrl() {
        return SafeApiUtil.postSafe_fundTrade_fund_reserve_setting_change_confirm();
    }

}
