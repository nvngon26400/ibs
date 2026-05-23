package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
public class IfaTrustFeeA002ApiResponse {
    
    /** 信託報酬リスト. */
    private List<IfaTrustFeeA002TrustFeeApiResponse> trustFeeList;
    
}
