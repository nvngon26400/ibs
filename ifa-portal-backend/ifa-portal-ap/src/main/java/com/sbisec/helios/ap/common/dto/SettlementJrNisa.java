package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalanceの受渡日ごとの決済情報(JrNISA) */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettlementJrNisa {

  @lombok.Getter(onMethod = @__(@JsonProperty("settlement_date_t_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settlement_date_t_jrnisa")))
  private String settlementDateTJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("unsettled_buy_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("unsettled_buy_total_jrnisa")))
  private String unsettledBuyTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("unexec_pay_bg_loss_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("unexec_pay_bg_loss_total_jrnisa")))
  private String unexecPayBgLossTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("open_buy_order_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("open_buy_order_total_jrnisa")))
  private String openBuyOrderTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("unsettled_sell_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("unsettled_sell_total_jrnisa")))
  private String unsettledSellTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("daytrade_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("daytrade_total_jrnisa")))
  private String daytradeTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("cash_payment_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("cash_payment_total_jrnisa")))
  private String cashPaymentTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("cash_receipt_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("cash_receipt_total_jrnisa")))
  private String cashReceiptTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settlement_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settlement_total_jrnisa")))
  private String settlementTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("account_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("account_total_jrnisa")))
  private String accountTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("buying_power_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("buying_power_total_jrnisa")))
  private String buyingPowerTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settlement_bk_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settlement_bk_total_jrnisa")))
  private String settlementBkTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settle_et_balance_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settle_et_balance_jrnisa")))
  private String settleEtBalanceJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_balance_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_balance_jrnisa")))
  private String settleBkBalanceJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_balance_cash_hold_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_balance_cash_hold_jrnisa")))
  private String settleBkBalanceCashHoldJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("security_cash_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("security_cash_total_jrnisa")))
  private String securityCashTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("keep_cash_total_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("keep_cash_total_jrnisa")))
  private String keepCashTotalJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("trans_jrnisa_amount_limit")))
  @lombok.Setter(onMethod = @__(@JsonProperty("trans_jrnisa_amount_limit")))
  private String transJrnisaAmountLimit;

  @lombok.Getter(onMethod = @__(@JsonProperty("trans_jrnisa_amount")))
  @lombok.Setter(onMethod = @__(@JsonProperty("trans_jrnisa_amount")))
  private String transJrnisaAmount;

  public SettlementJrNisa(
      String settlementDateTJrnisa,
      String unsettledBuyTotalJrnisa,
      String unexecPayBgLossTotalJrnisa,
      String openBuyOrderTotalJrnisa,
      String unsettledSellTotalJrnisa,
      String daytradeTotalJrnisa,
      String cashPaymentTotalJrnisa,
      String cashReceiptTotalJrnisa,
      String settlementTotalJrnisa,
      String accountTotalJrnisa,
      String buyingPowerTotalJrnisa,
      String settlementBkTotalJrnisa,
      String settleEtBalanceJrnisa,
      String settleBkBalanceJrnisa,
      String settleBkBalanceCashHoldJrnisa,
      String securityCashTotalJrnisa,
      String keepCashTotalJrnisa,
      String transJrnisaAmountLimit,
      String transJrnisaAmount) {
    this.settlementDateTJrnisa = settlementDateTJrnisa;
    this.unsettledBuyTotalJrnisa = unsettledBuyTotalJrnisa;
    this.unexecPayBgLossTotalJrnisa = unexecPayBgLossTotalJrnisa;
    this.openBuyOrderTotalJrnisa = openBuyOrderTotalJrnisa;
    this.unsettledSellTotalJrnisa = unsettledSellTotalJrnisa;
    this.daytradeTotalJrnisa = daytradeTotalJrnisa;
    this.cashPaymentTotalJrnisa = cashPaymentTotalJrnisa;
    this.cashReceiptTotalJrnisa = cashReceiptTotalJrnisa;
    this.settlementTotalJrnisa = settlementTotalJrnisa;
    this.accountTotalJrnisa = accountTotalJrnisa;
    this.buyingPowerTotalJrnisa = buyingPowerTotalJrnisa;
    this.settlementBkTotalJrnisa = settlementBkTotalJrnisa;
    this.settleEtBalanceJrnisa = settleEtBalanceJrnisa;
    this.settleBkBalanceJrnisa = settleBkBalanceJrnisa;
    this.settleBkBalanceCashHoldJrnisa = settleBkBalanceCashHoldJrnisa;
    this.securityCashTotalJrnisa = securityCashTotalJrnisa;
    this.keepCashTotalJrnisa = keepCashTotalJrnisa;
    this.transJrnisaAmountLimit = transJrnisaAmountLimit;
    this.transJrnisaAmount = transJrnisaAmount;
  }

  public SettlementJrNisa() {}
}
