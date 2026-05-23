package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 共通関数DTO：FCT009
 *
 * @author base 熊
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class OutputFct009Dto extends BaseOutputDto {
    
    //取得件数
    private String acquireNumber;
}
