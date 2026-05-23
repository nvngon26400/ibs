package com.sbisec.helios.gw.brokerageMenu.jointMarket.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0208_02
 * 画面名：共同店舗　信託報酬
 *
 * @author SBI大連　董
 2024/12/11 新規作成
 */
@Data
public class IfaJointMarketTrustFeeA002ApiResponse {
    
    /** 共同店舗　信託報酬リスト. */
    private List<IfaJointMarketTrustFeeA002TrustFeeApiResponse> trustFeeList;
    
}
