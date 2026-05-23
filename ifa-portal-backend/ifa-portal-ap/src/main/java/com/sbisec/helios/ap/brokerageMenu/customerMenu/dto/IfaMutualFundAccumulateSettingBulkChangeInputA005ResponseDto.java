package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 投信積立設定一括変更入力 A005 リスポンス
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto {

    /** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;

    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;

    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;


    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    /** お知らせアラート. */
    private String noticeAlert;
    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;


    // API003 積立設定リスト
    List<IfaMutualFundAccumulateSettingBulkChangeConfirmData> bulkChangeList;


    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法受付方法. */
    private String receiveMethod;

    // 注意事項
    private String checkMadoAki;

}
