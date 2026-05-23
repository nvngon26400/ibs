package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT033
 * @author base 熊
 */
@Data
public class OutputFct033Dto extends BaseOutputDto {

    /** 営業日チェックフラグ */
    private String businessDayCheckFlag;
}
