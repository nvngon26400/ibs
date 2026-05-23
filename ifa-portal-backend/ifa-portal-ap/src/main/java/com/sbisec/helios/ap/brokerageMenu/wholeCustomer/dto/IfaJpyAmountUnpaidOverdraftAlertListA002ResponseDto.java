package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
*
* @author BASE李
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto {
    /** 受渡日(1) */
    private String stlDate0;
    /** 仲介業者コード */
    private String brokerCode;
    /** 仲介業者支店名 */
    private String brokerBranchName;
    /** 仲介業者支店コード */
    private String subBrokerId;
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    /** 仲介業者担当者名 */
    private String employeeName;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 契約締結前交付書面コード名 */
    private String customerAttributeName;
    /** 顧客名_姓名(漢字) */
    private String customerNameKanji;
    /** 顧客名_姓名(カナ) */
    private String customerNameKana;
    /** 預り金赤残(1) */
    private String chargeAmount0;
    /** 仲介業者名 */
    private String brokerName;
    
    public IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto(IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel sql001ResModel) {
        this.stlDate0 = sql001ResModel.getStlDate0();
        this.brokerCode = sql001ResModel.getBrokerCode();
        this.brokerBranchName = sql001ResModel.getBrokerBranchName();
        this.subBrokerId = sql001ResModel.getSubBrokerId();
        this.brokerChargeCode = sql001ResModel.getBrokerChargeCode();
        this.employeeName = sql001ResModel.getEmployeeName();
        this.butenCode = sql001ResModel.getButenCode();
        this.accountNumber = sql001ResModel.getAccountNumber();
        this.customerAttributeName = sql001ResModel.getCustomerAttributeName();
        this.customerNameKanji = sql001ResModel.getCustomerNameKanji();
        this.customerNameKana = sql001ResModel.getCustomerNameKana();
        this.chargeAmount0 = sql001ResModel.getChargeAmount0();
        this.brokerName = sql001ResModel.getBrokerName();
    }
    
    
}
