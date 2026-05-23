package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT020
 * @author base 熊
 */
@Data
public class OutputFct020Dto extends BaseOutputDto {

    /** 銘柄コード. */
    private String brandCode;

    /** 市場コード. */
    private String marketCode;

    /** 評価用現在値. */
    private String currentValueForEvaluation;

}
