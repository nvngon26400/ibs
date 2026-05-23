package com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.form;

import lombok.Data;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */

@Data
public class IfaNewAccountOpenImcompleteStatusA002ApiResponseNewAccountOpenImcompleteStatus {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 営業員名（全角半角）. */
    private String brokerChargeName;

    /** 書類名（全角半角）. */
    private String documentName;

    /** 内容. */
    private String contents;

    /** 発送予定日. */
    private String shippingScheduleDate;

}
