package com.sbisec.helios.ap.athena.protocol.account;

import lombok.Data;

@Data
public class CashDeposit {
    /** 口座区分 */
    private String accountKind;
    /** 通貨コード */
    private String currencyCode;
    /** 預り金 */
    private String depositAmount;
    /** 円換算評価額 */
    private String evaluationAmount;
    /** 為替レート */
    private String exchangeRate;
}
