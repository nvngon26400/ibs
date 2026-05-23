package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT034
 * @author base 熊
 */

@Data
public class OutputFct034Dto extends BaseOutputDto {
    // 代用預りフラグ
    private String alternativeDepositFlag;

    // エラーコード
    private String errorCode;

}
