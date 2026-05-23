package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 積立設定変更前(後)データ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeConfirmData {

    /** 積立設定変更前データ */
    private IfaMutualFundAccumulateSettingBulkChangeData reserveSettingChangeBefore;

    /** 積立設定変更後データ */
    private IfaMutualFundAccumulateSettingBulkChangeData reserveSettingChangeAfter;

    /** コンプラランクチェック. */
    private IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck;

    /** アラート内容確認.コンプラランクチェック確認. */
    private String complianceRankCheckConfirm;

    /** アラート内容確認.コンプラランクチェック開始基準確認. */
    private String complianceRankCheckStartBaseConfirm;

    /** コンプラランクチェック.メッセージ. */
    private String message;

    /** コンプラランクチェック.チェックボックス文言. */
    private String invitationCheck;

    /** コンプラランクチェック.開始基準確認メッセージ. */
    private String startCriteriaConfirmMsg;

}
