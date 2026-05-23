package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountBalanceOutData {

  @lombok.Getter(onMethod = @__(@JsonProperty("date")))
  @lombok.Setter(onMethod = @__(@JsonProperty("date")))
  private String date;

  @lombok.Getter(onMethod = @__(@JsonProperty("time")))
  @lombok.Setter(onMethod = @__(@JsonProperty("time")))
  private String time;

  @lombok.Getter(onMethod = @__(@JsonProperty("shubetu")))
  @lombok.Setter(onMethod = @__(@JsonProperty("shubetu")))
  private String shubetu;

  @lombok.Getter(onMethod = @__(@JsonProperty("code")))
  @lombok.Setter(onMethod = @__(@JsonProperty("code")))
  private String code;

  @lombok.Getter(onMethod = @__(@JsonProperty("message")))
  @lombok.Setter(onMethod = @__(@JsonProperty("message")))
  private String message;

  @lombok.Getter(onMethod = @__(@JsonProperty("buten_cd")))
  @lombok.Setter(onMethod = @__(@JsonProperty("buten_cd")))
  private String butenCd;

  @lombok.Getter(onMethod = @__(@JsonProperty("koza_no")))
  @lombok.Setter(onMethod = @__(@JsonProperty("koza_no")))
  private String kozaNo;

  @lombok.Getter(onMethod = @__(@JsonProperty("mrf")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mrf")))
  private String mrf;

  @lombok.Getter(onMethod = @__(@JsonProperty("mmf")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mmf")))
  private String mmf;

  @lombok.Getter(onMethod = @__(@JsonProperty("mtgbf")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mtgbf")))
  private String mtgbf;

  @lombok.Getter(onMethod = @__(@JsonProperty("cash_receipt")))
  @lombok.Setter(onMethod = @__(@JsonProperty("cash_receipt")))
  private String cashReceipt;

  @lombok.Getter(onMethod = @__(@JsonProperty("total_balance")))
  @lombok.Setter(onMethod = @__(@JsonProperty("total_balance")))
  private String totalBalance;

  @lombok.Getter(onMethod = @__(@JsonProperty("auto_sweep_kbn")))
  @lombok.Setter(onMethod = @__(@JsonProperty("auto_sweep_kbn")))
  private String autoSweepKbn;

  @lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_bargain_max")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_bargain_max")))
  private String settleBkBargainMax;

  @lombok.Getter(onMethod = @__(@JsonProperty("et_need_cash_rate")))
  @lombok.Setter(onMethod = @__(@JsonProperty("et_need_cash_rate")))
  private String etNeedCashRate;

  @lombok.Getter(onMethod = @__(@JsonProperty("security_cash")))
  @lombok.Setter(onMethod = @__(@JsonProperty("security_cash")))
  private String securityCash;

  @lombok.Getter(onMethod = @__(@JsonProperty("keep_cash")))
  @lombok.Setter(onMethod = @__(@JsonProperty("keep_cash")))
  private String keepCash;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit")))
  private String isaBuyLimit;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit_next")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit_next")))
  private String isaBuyLimitNext;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_year")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_year")))
  private String isaBuyYear;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_year_next")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_year_next")))
  private String isaBuyYearNext;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_this_year_kbn")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_this_year_kbn")))
  private String isaThisYearKbn;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_next_year_kbn")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_next_year_kbn")))
  private String isaNextYearKbn;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit_stop")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit_stop")))
  private String isaBuyLimitStop;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit_stop_next")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit_stop_next")))
  private String isaBuyLimitStopNext;

  @lombok.Getter(onMethod = @__(@JsonProperty("mrf_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mrf_jrnisa")))
  private String mrfJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("mmf_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mmf_jrnisa")))
  private String mmfJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("mtgbf_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("mtgbf_jrnisa")))
  private String mtgbfJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("cash_receipt_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("cash_receipt_jrnisa")))
  private String cashReceiptJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("total_balance_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("total_balance_jrnisa")))
  private String totalBalanceJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("auto_sweep_kbn_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("auto_sweep_kbn_jrnisa")))
  private String autoSweepKbnJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("settle_bk_bargain_max_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("settle_bk_bargain_max_jrnisa")))
  private String settleBkBargainMaxJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("et_need_cash_rate_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("et_need_cash_rate_jrnisa")))
  private String etNeedCashRateJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("security_cash_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("security_cash_jrnisa")))
  private String securityCashJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("keep_cash_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("keep_cash_jrnisa")))
  private String keepCashJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit_jrnisa")))
  private String isaBuyLimitJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_limit_next_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_limit_next_jrnisa")))
  private String isaBuyLimitNextJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_year_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_year_jrnisa")))
  private String isaBuyYearJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("isa_buy_year_next_jrnisa")))
  @lombok.Setter(onMethod = @__(@JsonProperty("isa_buy_year_next_jrnisa")))
  private String isaBuyYearNextJrnisa;

  @lombok.Getter(onMethod = @__(@JsonProperty("jnisa_seigen_flg")))
  @lombok.Setter(onMethod = @__(@JsonProperty("jnisa_seigen_flg")))
  private String jnisaSeigenFlg;
  
  @lombok.Getter(onMethod = @__(@JsonProperty("buying_power_total")))
  @lombok.Setter(onMethod = @__(@JsonProperty("buying_power_total")))
  private String buyingPowerTotal;

  @lombok.Getter(onMethod = @__(@JsonProperty("t0")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t0")))
  private Settlement t0;

  @lombok.Getter(onMethod = @__(@JsonProperty("t1")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t1")))
  private Settlement t1;

  @lombok.Getter(onMethod = @__(@JsonProperty("t2")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t2")))
  private Settlement t2;

  @lombok.Getter(onMethod = @__(@JsonProperty("t3")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t3")))
  private Settlement t3;

  @lombok.Getter(onMethod = @__(@JsonProperty("t4")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t4")))
  private Settlement t4;

  @lombok.Getter(onMethod = @__(@JsonProperty("t5")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t5")))
  private Settlement t5;

  @lombok.Getter(onMethod = @__(@JsonProperty("t0_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t0_jr")))
  private SettlementJrNisa t0Jr;

  @lombok.Getter(onMethod = @__(@JsonProperty("t1_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t1_jr")))
  private SettlementJrNisa t1Jr;

  @lombok.Getter(onMethod = @__(@JsonProperty("t2_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t2_jr")))
  private SettlementJrNisa t2Jr;

  @lombok.Getter(onMethod = @__(@JsonProperty("t3_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t3_jr")))
  private SettlementJrNisa t3Jr;

  @lombok.Getter(onMethod = @__(@JsonProperty("t4_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t4_jr")))
  private SettlementJrNisa t4Jr;

  @lombok.Getter(onMethod = @__(@JsonProperty("t5_jr")))
  @lombok.Setter(onMethod = @__(@JsonProperty("t5_jr")))
  private SettlementJrNisa t5Jr;

 
  
  public QueryAccountBalanceOutData(
      String date,
      String time,
      String shubetu,
      String code,
      String message,
      String butenCd,
      String kozaNo,
      String mrf,
      String mmf,
      String mtgbf,
      String cashReceipt,
      String totalBalance,
      String autoSweepKbn,
      String settleBkBargainMax,
      String etNeedCashRate,
      String securityCash,
      String keepCash,
      String isaBuyLimit,
      String isaBuyLimitNext,
      String isaBuyYear,
      String isaBuyYearNext,
      String isaThisYearKbn,
      String isaNextYearKbn,
      String isaBuyLimitStop,
      String isaBuyLimitStopNext,
      String mrfJrnisa,
      String mmfJrnisa,
      String mtgbfJrnisa,
      String cashReceiptJrnisa,
      String totalBalanceJrnisa,
      String autoSweepKbnJrnisa,
      String settleBkBargainMaxJrnisa,
      String etNeedCashRateJrnisa,
      String securityCashJrnisa,
      String keepCashJrnisa,
      String isaBuyLimitJrnisa,
      String isaBuyLimitNextJrnisa,
      String isaBuyYearJrnisa,
      String isaBuyYearNextJrnisa,
      String jnisaSeigenFlg,
      String buyingPowerTotal,
      Settlement t0,
      Settlement t1,
      Settlement t2,
      Settlement t3,
      Settlement t4,
      Settlement t5,
      SettlementJrNisa t0Jr,
      SettlementJrNisa t1Jr,
      SettlementJrNisa t2Jr,
      SettlementJrNisa t3Jr,
      SettlementJrNisa t4Jr,
      SettlementJrNisa t5Jr) {
    this.date = date;
    this.time = time;
    this.shubetu = shubetu;
    this.code = code;
    this.message = message;
    this.butenCd = butenCd;
    this.kozaNo = kozaNo;
    this.mrf = mrf;
    this.mmf = mmf;
    this.mtgbf = mtgbf;
    this.cashReceipt = cashReceipt;
    this.totalBalance = totalBalance;
    this.autoSweepKbn = autoSweepKbn;
    this.settleBkBargainMax = settleBkBargainMax;
    this.etNeedCashRate = etNeedCashRate;
    this.securityCash = securityCash;
    this.keepCash = keepCash;
    this.isaBuyLimit = isaBuyLimit;
    this.isaBuyLimitNext = isaBuyLimitNext;
    this.isaBuyYear = isaBuyYear;
    this.isaBuyYearNext = isaBuyYearNext;
    this.isaThisYearKbn = isaThisYearKbn;
    this.isaNextYearKbn = isaNextYearKbn;
    this.isaBuyLimitStop = isaBuyLimitStop;
    this.isaBuyLimitStopNext = isaBuyLimitStopNext;
    this.mrfJrnisa = mrfJrnisa;
    this.mmfJrnisa = mmfJrnisa;
    this.mtgbfJrnisa = mtgbfJrnisa;
    this.cashReceiptJrnisa = cashReceiptJrnisa;
    this.totalBalanceJrnisa = totalBalanceJrnisa;
    this.autoSweepKbnJrnisa = autoSweepKbnJrnisa;
    this.settleBkBargainMaxJrnisa = settleBkBargainMaxJrnisa;
    this.etNeedCashRateJrnisa = etNeedCashRateJrnisa;
    this.securityCashJrnisa = securityCashJrnisa;
    this.keepCashJrnisa = keepCashJrnisa;
    this.isaBuyLimitJrnisa = isaBuyLimitJrnisa;
    this.isaBuyLimitNextJrnisa = isaBuyLimitNextJrnisa;
    this.isaBuyYearJrnisa = isaBuyYearJrnisa;
    this.isaBuyYearNextJrnisa = isaBuyYearNextJrnisa;
    this.jnisaSeigenFlg = jnisaSeigenFlg;
    this.buyingPowerTotal = buyingPowerTotal;
    this.t0 = t0;
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t0Jr = t0Jr;
    this.t1Jr = t1Jr;
    this.t2Jr = t2Jr;
    this.t3Jr = t3Jr;
    this.t4Jr = t4Jr;
    this.t5Jr = t5Jr;
  }

  public QueryAccountBalanceOutData() {}
}
