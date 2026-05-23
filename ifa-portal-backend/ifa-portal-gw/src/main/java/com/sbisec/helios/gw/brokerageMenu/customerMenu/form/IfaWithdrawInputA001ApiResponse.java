package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import jp.co.sbisec.pcenter.dto.yanap.PayEstimateData;
import lombok.Data;

/**
 * 出金入力A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA001ApiResponse {
    
    /** 出金可能金額 */
    private String acBalance;

    /** 振込先銀行口座 */
    private List<IfaWithdrawInputA001ApiResponseBankAccountInfo> bankAccountInfo;

    /** 出金明細リスト */
    private List<PayEstimateData> payEstimateData;

}
