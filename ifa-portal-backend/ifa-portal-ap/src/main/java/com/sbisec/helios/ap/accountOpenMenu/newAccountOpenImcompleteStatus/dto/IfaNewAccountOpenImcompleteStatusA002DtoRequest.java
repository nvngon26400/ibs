package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */

@Data
public class IfaNewAccountOpenImcompleteStatusA002DtoRequest {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外フラグ. */
    private String chkBrokerCodeExclude;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 登録日From. */
    private String dispatchScheduleDateFrom;

    /** 登録日To. */
    private String dispatchScheduleDateTo;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;
}
