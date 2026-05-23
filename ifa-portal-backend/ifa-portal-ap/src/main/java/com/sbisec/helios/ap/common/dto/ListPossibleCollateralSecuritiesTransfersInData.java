package com.sbisec.helios.ap.common.dto;

import lombok.Data;

@Data
public class ListPossibleCollateralSecuritiesTransfersInData {

    // Header.token
    private String headerToken;

    // パラメータ
    private Parameter parameter;

    @Data
    public static class Parameter {

        // 商品コード
        private String productCode;

        // 国コード
        private String countryCode;

        // 通貨コード
        private String currencyCode;
    }

}
