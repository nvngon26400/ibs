package com.sbisec.helios.gw.brokerageMenu.jointMarket.form;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Data
public class IfaJointMarketTradeSearchA002ApiRequest {

    /** 仲介業者コード */
    @Pattern(regexp = "[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    @Size(max = 49, message = "仲介業者コード")
    private String brokerCode;

    /** 仲介業者除外（半角英数字） */
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;

    /** 支店コード（数字） */
    @Pattern(regexp = "0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    @NotEmpty(message = "営業員コード")
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;

    /** 部店コード（半角英数字）. */
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;

    /** 口座番号（数字）. */
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;

    /** 顧客名（全角半角）. */
    @NotEmpty(message = "顧客名")
    @Size(max = 100, message = "顧客名")
    private String customerName;

    /** 顧客名検索オプション. */
    @NotEmpty(message = "顧客名検索オプション")
    private String customerNameSearchType;

    /** 取引コース（全角半角）. */
    @NotEmpty(message = "取引コース")
    @Size(max = 40, message = "取引コース")
    private List<IfaJointMarketTradeSearchApiRequestSelected> course;

    /** 期間指定From. */
    @NotEmpty(message = "期間指定From")
    @Size(min = 10, max = 10, message = "期間指定From")
    private String periodDateFrom;

    /** 期間指定To. */
    @NotEmpty(message = "期間指定To")
    @Size(min = 10, max = 10, message = "期間指定To")
    private String periodDateTo;

    /** 証券種別. */
    @NotEmpty(message = "証券種別")
    private List<IfaJointMarketTradeSearchApiRequestSelected> securityClass;

    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 12, message = "銘柄コード")
    private String brandCode12;

}
