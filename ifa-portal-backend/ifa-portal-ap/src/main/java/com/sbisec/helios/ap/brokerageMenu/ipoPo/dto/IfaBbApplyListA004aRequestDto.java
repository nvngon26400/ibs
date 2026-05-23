package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListA004aRequestDto {
    /** 仲介業者コード */
    private String brokerCode;
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    /** 支店コード */
    private String branchCode;
    /** 営業員コード */
    private String empCode;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 顧客名（漢字／カナ） */
    private String customerNameKanjiKana;
    /** 顧客名（漢字／カナ）_条件 */
    private String customerNameKanjiKanaTerms;
    /** 取引コース */
    private String course;
    /** 銘柄コード */
    private String brandCode;
    /** 抽選結果 */
    private String lotteryResult;
    /** 注文状況 */
    private String orderStatus;
    /** 過去の申込も表示する */
    private String historyInclude;
    /** 営業員自動判定コード */
    private String empCodeAutoDispFlag;
}
