package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeA002RequestDto {

    /** 仲介業者コード（数字） */
    private List<String> brokerCodeArray;

    /** 仲介業者除外（半角英数字） */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字） */
    private String branchCode;

    /** 営業員コード（半角英数字） */
    private String empCode;

    /** 表示内容 */
    private String displayContent;

    /** 集計単位(日次/月次) */
    private String dailyMonthlyCountingUnit;

    /** 集計単位(仲介業者/営業員/支店) */
    private String brokerChargeBranchCountingUnit;

    /** 集計単位(集計/扱者) */
    private String aggregatorHandlerCountingUnit;

    /** 期間指定FROM */
    private String periodFrom;

    /** 期間指定TO */
    private String periodTo;

}
