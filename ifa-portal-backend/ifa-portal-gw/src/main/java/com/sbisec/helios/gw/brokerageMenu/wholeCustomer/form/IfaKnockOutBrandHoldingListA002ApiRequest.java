package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockOutBrandHoldingListA002ApiRequest {

    /** 仲介業者コード（数字）. */
    @Size(max = 49)
    @Pattern(regexp = "[a-zA-Z0-9\\,]*")
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    @Pattern(regexp = "0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;

    /** 部店コード（半角英数字）. */
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;

    /** 口座番号（数字）. */
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;

    /** 顧客名（漢字／カナ）（全角半角）. */
    @Size(max = 72, message = "顧客名（漢字／カナ）")
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 取引コース. */
    @NotEmpty(message = "取引コース")
    private List<IfaKnockOutBrandHoldingListA002ApiRequestCourseSelected> courseSelected;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

}
