package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定変更入力 A009 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingChangeInputA009ResponseDto {

    /** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;
    
    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;
    
    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** 銘柄コード. */
    private String brandCode;

    /** 銘柄名. */
    private String brandName;

    /** 協会コード. */
    private String fundCode;

    /** 決済方法. */
    private String paymentMethod;

    /** 預り区分. */
    private String accountType;
    private String accountTypeName;

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    /** コンプラランクチェック. */
    private IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    /** お知らせアラート. */
    private String noticeAlert;
    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法受付方法. */
    private String receiveMethod;

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** 積立設定変更前データ */
    private IfaMutualFundAccumulateSettingChangeData reserveSettingChangeBefore;

    /** 積立設定変更後データ */
    private IfaMutualFundAccumulateSettingChangeData reserveSettingChangeAfter;

}
