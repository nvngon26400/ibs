package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputData;

import lombok.Data;

/**
 * 投信積立設定一括変更確認 A001 リクエストパラメータ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiRequest {

    // ジュニアNISA口座開設有無
    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    // 明細リスト
    List<IfaMutualFundAccumulateSettingBulkChangeInputData> bulkChangeInputDataList;

    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    private String kanyuKbn;

    /** 受付方法. */
    @NotEmpty(message = "受付方法")
    private String receiveMethod;

    // ご注意事項
    @NotEmpty(message = "注意事項 説明済確認")
    @Size(min = 1, max = 1, message = "注意事項 説明済確認")
    private String checkMadoAki;

    /** アラート内容確認.注意情報アラート確認. */
    private String noticeInfoAlertConfirm;

    /** アラート内容確認.お知らせアラート確認. */
    private String noticeAlertConfirm;

    /** アラート内容確認.確認書受け入れアラート確認. */
    private String confirmDocumentAlertConfirm;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角）. */
    private String noticeAlert;

    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

}
