package com.sbisec.helios.ap.safe.service.fund.product.dto;

import com.sbisec.helios.ap.safe.service.common.dto.DtoIn;

/**
 * FundProductServiceのInputのDtoベース
 */
public abstract class FundProductDtoIn extends DtoIn {

    /**
     * コンストラクタ
     * @param id APIアクセス時のid
     */
    public FundProductDtoIn(final String id) {
        super("fundProduct", "1.0.0", id);
    }
}
