package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeSql001To012RequestModel {

    /** 期間指定From */
    private String periodFrom;

    /** 期間指定To */
    private String periodTo;

    /** 仲介業者コード */
    private List<String> brokerCodeArray;

    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;

    /** 支店コード */
    private String branchCode;

    /** 営業員コード */
    private String empCode;

    /** 表示内容 */
    private String displayContent;

    /** 集計単位(日次/月次) */
    private String dailyMonthlyCountingUnit;

    /** 集計単位(仲介業者/営業員/支店) */
    private String brokerChargeBranchCountingUnit;

    /** 集計単位(集計/扱者) */
    private String aggregatorHandlerCountingUnit;

    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;

    /** 最大件数 */
    private int maxRowNum;

    /** ユーザ共通情報.権限コード  */
    private String privId;

    /** 取得パターン */
    private String pattern;

}
