package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Data
public class IfaJointSubscriptTrustFeeA002DtoResponse {
    
    /** 信託報酬リスト */
    private List<IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse> trustFeeList;
    
}
