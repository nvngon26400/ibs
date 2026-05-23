package com.sbisec.helios.ap.safe.protocol.fundProduct;

import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFundDetailReq implements SafeBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private FundDetailInfoApiIn parameter = new FundDetailInfoApiIn();

    @Data
    @NoArgsConstructor
    public class Header {

        private String token;

    }

    public String getSafeApiUrl() {
        return SafeApiUtil.getSafe_fundProduct_fund_detail();
    }

}
