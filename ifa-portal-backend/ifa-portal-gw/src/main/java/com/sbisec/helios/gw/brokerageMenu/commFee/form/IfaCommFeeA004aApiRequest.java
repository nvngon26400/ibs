package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeA004aApiRequest {

    /** 仲介業者コード（数字） */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp="[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    @Size(max = 49, message = "仲介業者コード")
    private String brokerCode;

    /** 仲介業者除外（半角英数字） */
    @NotEmpty(message = "仲介業者除外")
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;

    /** 支店コード（数字） */
    @NotEmpty(message = "支店コード")
    @Pattern(regexp = "0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;

    /** 営業員コード（半角英数字） */
    @Pattern(regexp = "(^[a-zA-Z0-9]{4}$)|(^$)", message = "営業員コード")
    private String empCode;

    /** 表示内容 */
    @NotEmpty(message = "表示内容")
    private String displayContent;

    /** 集計単位(日次/月次) */
    @NotEmpty(message = "集計単位(日次/月次)")
    private String dailyMonthlyCountingUnit;

    /** 集計単位(仲介業者/営業員/支店) */
    @NotEmpty(message = "集計単位(仲介業者/営業員/支店)")
    private String brokerChargeBranchCountingUnit;

    /** 集計単位(集計/扱者) */
    @NotEmpty(message = "集計単位(集計/扱者)")
    private String aggregatorHandlerCountingUnit;

    /** 期間指定_入力 */
    @NotEmpty(message = "期間指定_入力")
    private List<String> periodYmInput;

}
