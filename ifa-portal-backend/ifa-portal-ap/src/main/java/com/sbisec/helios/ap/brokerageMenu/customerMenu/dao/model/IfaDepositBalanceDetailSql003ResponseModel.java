package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 画面ID：SUB0202_010201-04
 * 画面名：預り残高詳細
 * 2025/02/05 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaDepositBalanceDetailSql003ResponseModel {

    /** 利率 */
    private String compoundInterest;

    /** 償還日 */
    private String redemptionDate;

    /** 利払月日1 */
    private String interestPaymentDate1;

    /** 利払月日2 */
    private String interestPaymentDate2;

    /** 利払月日3 */
    private String interestPaymentDate3;

    /** 利払月日4 */
    private String interestPaymentDate4;

    /** 利払区分 */
    private String interestPaymentKbn;

    /** 利払日月末表示区分 */
    private String interestPaymentDateGetsumatsuKbn;

    /** 利払月1 */
    public String getInterestPaymentMonth1() {
        return StringUtils.substring(this.interestPaymentDate1, 0, 2);
    }

    /** 利払月2 */
    public String getInterestPaymentMonth2() {
        return StringUtils.substring(this.interestPaymentDate2, 0, 2);
    }

    /** 利払月3 */
    public String getInterestPaymentMonth3() {
        return StringUtils.substring(this.interestPaymentDate3, 0, 2);
    }

    /** 利払月4 */
    public String getInterestPaymentMonth4() {
        return StringUtils.substring(this.interestPaymentDate4, 0, 2);
    }

    /** 利払日1 */
    public String getInterestPaymentDay1() {
        return StringUtils.substring(this.interestPaymentDate1, 2, 4);
    }

    /** 利払日2 */
    public String getInterestPaymentDay2() {
        return StringUtils.substring(this.interestPaymentDate2, 2, 4);
    }

    /** 利払日3 */
    public String getInterestPaymentDay3() {
        return StringUtils.substring(this.interestPaymentDate3, 2, 4);
    }

    /** 利払日4 */
    public String getInterestPaymentDay4() {
        return StringUtils.substring(this.interestPaymentDate4, 2, 4);
    }

}
