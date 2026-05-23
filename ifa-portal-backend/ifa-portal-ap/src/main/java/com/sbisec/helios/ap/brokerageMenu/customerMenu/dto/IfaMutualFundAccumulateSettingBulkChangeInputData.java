package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 積立設定変更前(後)データ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeInputData {

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    // 銘柄コード
    private String brandCode;

    /** 協会コード */
    private String fundCode;

    /** 預り区分 */
    private String accountType;

    /** 設定金額 */
    private String settingAmount;

    /** コース区分 */
    private String courseType;

    /** 積立日付 */
    private String settingReserveDay;

    /** 積立隔月設定 */
    private String settingReserveBimonthly;

    /** 積立毎週設定 */
    private String settingReserveWeek;

    /** 積立複数日設定 */
    private String settingReserveMultiDay;

    /** コンプラランクチェック. */
    private IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck;

    /** アラート内容確認.コンプラランクチェック確認. */
    private String complianceRankCheckConfirm;

    /** アラート内容確認.コンプラランクチェック開始基準確認. */
    private String complianceRankCheckStartBaseConfirm;

}
