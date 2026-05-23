package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT020
 * @author base 熊
 */
@Data
public class InputFct020Dto {

    /** 銘柄コード */
    private String brandCode;

    /** 権利区分 */
    private String rightType;

    /** CT夜間バッチ終了フラグ */
    private String ctNightBatchEndFlag;

    /** 市場コード */
    private String marketCode;

}
