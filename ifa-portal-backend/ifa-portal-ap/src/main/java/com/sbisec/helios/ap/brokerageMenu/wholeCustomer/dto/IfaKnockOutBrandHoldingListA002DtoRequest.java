package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockOutBrandHoldingListA002DtoRequest {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 取引コース（全角半角）. */
    private List<IfaKnockOutBrandHoldingListA002DtoRequestCourseSelected> courseSelected;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

}
