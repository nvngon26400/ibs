package com.sbisec.helios.gw.common.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 */
@Data
public class CcsLoginRequestForm {
    
    /** CCS画面ID */
    @NotNull(message = "CCS画面ID")
    private String ccsDispId;
    
}
