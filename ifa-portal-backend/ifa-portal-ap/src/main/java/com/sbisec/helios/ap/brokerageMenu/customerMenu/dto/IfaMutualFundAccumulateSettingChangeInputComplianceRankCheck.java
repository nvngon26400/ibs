package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * コンプラランクチェック
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck {

    /** メッセージ. */
    private String message;

    /** チェックボックス文言 */
    private String invitationCheck;

    /** 開始基準確認メッセージ. */
    private String startCriteriaConfirmMsg;

}
