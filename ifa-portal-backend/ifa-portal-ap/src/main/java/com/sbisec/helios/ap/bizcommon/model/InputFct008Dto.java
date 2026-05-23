package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT008
 *
 * @author SCSK
 */

@Data
public class InputFct008Dto {
    
    // 銘柄コード
    private String brandCode;
    
    // 期間対象市場
    private String periodTargetMarket;

}
