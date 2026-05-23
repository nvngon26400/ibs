package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001ResponseModel;

import jp.co.sbisec.pcenter.dto.yanap.PayEstimateData;
import lombok.Data;

/**
 * 出金入力A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA001ResponseDto {
    
    /** 出金可能金額 */
    private String acBalance;

    /** 出金可能日From */
    private String paymentDayFrom;

    /** 出金可能日To */
    private String paymentDayTo;

    /** 振込先銀行口座 */
    private List<IfaWithdrawInputSql001ResponseModel> bankAccountInfo;

    /** 出金明細リスト */
    private List<PayEstimateData> payEstimateData;

}
