package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 投信積立設定一括変更確認 A001 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto {

    /** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;

    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;

    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;

    // ジュニアNISA口座開設有無
    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    // API003 積立設定リスト
    List<IfaMutualFundAccumulateSettingBulkChangeConfirmData> bulkChangeList;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法受付方法. */
    private String receiveMethod;

    /** 確認項目.ご注意事項. */
    private String checkMadoAki;

//    /** アラート内容確認.コンプラランクチェック確認. */
//    private String complianceRankCheckConfirm;
//
//    /** アラート内容確認.コンプラランクチェック開始基準確認. */
//    private String complianceRankCheckStartBaseConfirm;

    /** アラート内容確認.注意情報アラート確認. */
    private String noticeInfoAlertConfirm;

    /** アラート内容確認.お知らせアラート確認. */
    private String noticeAlertConfirm;

    /** アラート内容確認.確認書受け入れアラート確認. */
    private String confirmDocumentAlertConfirm;

    /** 注意情報アラート. */
    private String noticeInfoAlert;

    /** お知らせアラート. */
    private String noticeAlert;

//    /** コンプラランクチェック.メッセージ. */
//    private String message;

//    /** コンプラランクチェック.チェックボックス文言. */
//    private String invitationCheck;

//    /** コンプラランクチェック.開始基準確認メッセージ. */
//    private String startCriteriaConfirmMsg;

    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;


    /** リクエスト内容. */
    private IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto requestContents;

}
