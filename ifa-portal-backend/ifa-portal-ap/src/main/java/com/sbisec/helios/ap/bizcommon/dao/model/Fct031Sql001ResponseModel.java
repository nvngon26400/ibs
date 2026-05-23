package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * 顧客情報取得レスポンス
 *
 * @author SCSK
 *
 */
@Data
public class Fct031Sql001ResponseModel {
    
    // 顧客コード
    private int customerId;
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private int accountNumber;
    
    // 顧客名（漢字）
    private String nameKanji;
    
    // 顧客名（カナ）
    private String nameKana;
    
    // コンプラランク
    private String tcCompRank;
    
    // 契約締結前交付書面コード
    private String customerAttribute;
    
    // 契約締結前交付書面コード名
    private String customerAttributeName;
    
    // 扱者コード
    private String dealerNumber;
    
    // 生年月日
    private String birthDay;
    
    // 年齢
    private String age;
    
    // 仲介業者コード
    private String brokerCode;
    
    // 仲介業者営業員コード
    private String intermediaryEmpCd;
    
    // 法人区分
    private String uaiCorporationKbn;
    
    // 自宅電話番号(市外局番) 
    private String uaiPhoneLongDist;
    
    // 自宅電話番号(市内局番)
    private String uaiPhoneCityCode;
    
    // 自宅電話番号(番号)
    private String uaiPhoneNumber;
    
    // 携帯電話番号(上5桁) 
    private String uaiCellularNumber1;
    
    // 携帯電話番号(中5桁) 
    private String uaiCellularNumber2;
    
    // 携帯電話番号(下5桁)
    private String uaiCellularNumber3;
    
    // 代表者名(漢字)
    private String uaiDaihyoNameKanji;
    
    // 代表者名(カナ)
    private String uaiDaihyoNameKana;
    
    // 代理人名(漢字)
    private String uaiDairiNameKanji;
    
    // 代理人名(カナ)
    private String uaiDairiNameKana;
    
    // 代理人肩書区分
    private String uaiOfficeDairiKbn;
    
    // 特定口座区分
    private String uaiSpecificKbn;
    
    // 特定口座配当受入開始日
    private String uaiSpecificDividendStartDt;
    
    // 特定口座配当受入終了日
    private String uaiSpecificDividendEndDt;
    
    // マル優適格者区分
    private String uaiMaruyuTekikakushaKbn;
    
    // ISA契約区分
    private String uaiIsaContractKbn;
    
    // ジュニアISA契約区分
    private String uaiJnisaContractKbn;
    
    // ジュニアNISA口座番号
    private String uaiJnisaKozaNo;
    
    // ジュニア特定口座区分
    private String uaiJnisaSpecificKbn;
    
    // 仲介業者名
    private String brokerName;
    
    // 仲介業者支店名
    private String branchName;
    
    // 仲介業者営業員名
    private String brokerChargeName;
    
    // 注意情報エラー件数
    private int noteInfoErrorCount;
    
    // 注意情報件数
    private int noteInfoCount;
    
    // 未読の重要なお知らせによる取引制限エラー件数
    private int unreadImportantNoticeTransactionLimitErrorNumber;
    
    // 未読の重要なお知らせによる取引制限件数
    private int unreadImportantNoticeTransactionLimitNumber;
    
}
