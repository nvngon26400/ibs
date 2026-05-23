package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListSql001RequestModel {
    /** 銘柄コード */
    private String brandCode;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 支店コード */
    private String branchCode;
    /** 仲介業者コード */
    private String brokerCode;
    /** 営業員コード */
    private String empCode;
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    /** 顧客名（漢字／カナ） */
    private String customerNameKanjiKana;
    /** 顧客名（漢字／カナ）_条件 */
    private String customerNameKanjiKanaTerms;
    /** 取引コース */
    private String course;
    /** 仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    /** ユーザ共通情報.権限Id */
    private String privId;
    /** 抽選結果 */
    private String lotteryResult;
    /** 注文状況 */
    private String orderStatus;
    /** 最大件数 */
    private String querySizeLimit;
    /** 過去の申込も表示する */
    private String historyInclude;
}
