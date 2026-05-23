package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

/**
 * 顧客一覧_先OP
 *
 * @author SCSK
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IfaCustomerListFuturesOptionsSql001RequestModel {

    /** 仲介業者コード（数字） */
    private List<String> brokerCodes;

    /** 支店コード（数字） */
    private String branchCode;

    /** 営業員コード（半角英数字） */
    private String empCode;

    /** 仲介業者除外（半角英数字） */
    private List<String> brokerCodesExclude;

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 顧客名(漢字/カナ)（全角半角） */
    private String customerNameKanjiKana;

    /** 取引コース（全角半角） */
    private List<String> courses;

    /** 必要委託保証金（From）（数値(整数)） */
    private String necessaryEntrustDepositFrom;

    /** 必要委託保証金（To）（数値(整数)） */
    private String necessaryEntrustDepositTo;

    /** 受入証拠金（From）（数値(整数)） */
    private String marginMoneyFrom;

    /** 受入証拠金（To）（数値(整数)） */
    private String marginMoneyTo;

    /** 前日評価損益（From）（数値(整数)） */
    private String beforeProfitAndLossFrom;

    /** 前日評価損益（To）（数値(整数)） */
    private String beforeProfitAndLossTo;

    /** 仲介業者営業員リスト(FCT030) */
    private List<BrokerCharge> brokerChargeList;

    /** ユーザ共通情報.権限コード */
    private String privId;

    /** 最大取得件数 */
    private int searchLimitRow;

}
