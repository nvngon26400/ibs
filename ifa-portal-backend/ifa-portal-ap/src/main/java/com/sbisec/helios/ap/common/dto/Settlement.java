package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalanceの受渡日ごとの決済情報 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settlement {

@lombok.Getter(onMethod = @__(@JsonProperty("settlement_date_t")))
@lombok.Setter(onMethod = @__(@JsonProperty("settlement_date_t")))
private String settlementDateT;

@lombok.Getter(onMethod = @__(@JsonProperty("unsettled_buy_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("unsettled_buy_total")))
private String unsettledBuyTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("unexec_pay_bg_loss_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("unexec_pay_bg_loss_total")))
private String unexecPayBgLossTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("open_buy_order_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("open_buy_order_total")))
private String openBuyOrderTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("unsettled_sell_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("unsettled_sell_total")))
private String unsettledSellTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("daytrade_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("daytrade_total")))
private String daytradeTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("cash_payment_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("cash_payment_total")))
private String cashPaymentTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("cash_receipt_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("cash_receipt_total")))
private String cashReceiptTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("settlement_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("settlement_total")))
private String settlementTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("account_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("account_total")))
private String accountTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("buying_power_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("buying_power_total")))
private String buyingPowerTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("settlement_bk_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("settlement_bk_total")))
private String settlementBkTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("settle_et_balance")))
@lombok.Setter(onMethod = @__(@JsonProperty("settle_et_balance")))
private String settleEtBalance;

@lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_balance")))
@lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_balance")))
private String settleBkBalance;

@lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_balance_cash_hold")))
@lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_balance_cash_hold")))
private String settleBkBalanceCashHold;

@lombok.Getter(onMethod = @__(@JsonProperty("security_cash_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("security_cash_total")))
private String securityCashTotal;

@lombok.Getter(onMethod = @__(@JsonProperty("keep_cash_total")))
@lombok.Setter(onMethod = @__(@JsonProperty("keep_cash_total")))
private String keepCashTotal;

public Settlement(
   String settlementDateT,
   String unsettledBuyTotal,
   String unexecPayBgLossTotal,
   String openBuyOrderTotal,
   String unsettledSellTotal,
   String daytradeTotal,
   String cashPaymentTotal,
   String cashReceiptTotal,
   String settlementTotal,
   String accountTotal,
   String buyingPowerTotal,
   String settlementBkTotal,
   String settleEtBalance,
   String settleBkBalance,
   String settleBkBalanceCashHold,
   String securityCashTotal,
   String keepCashTotal) {
 this.settlementDateT = settlementDateT;
 this.unsettledBuyTotal = unsettledBuyTotal;
 this.unexecPayBgLossTotal = unexecPayBgLossTotal;
 this.openBuyOrderTotal = openBuyOrderTotal;
 this.unsettledSellTotal = unsettledSellTotal;
 this.daytradeTotal = daytradeTotal;
 this.cashPaymentTotal = cashPaymentTotal;
 this.cashReceiptTotal = cashReceiptTotal;
 this.settlementTotal = settlementTotal;
 this.accountTotal = accountTotal;
 this.buyingPowerTotal = buyingPowerTotal;
 this.settlementBkTotal = settlementBkTotal;
 this.settleEtBalance = settleEtBalance;
 this.settleBkBalance = settleBkBalance;
 this.settleBkBalanceCashHold = settleBkBalanceCashHold;
 this.securityCashTotal = securityCashTotal;
 this.keepCashTotal = keepCashTotal;
}

public Settlement() {}
}
