package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * 2025/2/20 新規作成
 * 
 * @author lianhua.xia
 */

@Data
public class IfaCustomerPortalSql008ResponseModel {

    /** 本人職業区分 */
    private String uaiOccupation;
    
    /** 勤務先名(漢字)（全角半角）. */
    private String uaiOfficeName;
    
    /** 勤務先電話番号(市外局番)) */
    private String uaiOfficePhoneLongDist;
    
    /** 勤務先電話番号(市内局番)) */
    private String uaiOfficePhoneCityCode;
    
    /** 勤務先電話番号(番号) */
    private String uaiOfficePhoneNumber;
    
    /** 代表者肩書区分 */
    private String uaiOfficeDaihyoKbn;
    
    /** 決算期1 */
    private String uaiCorpSettlementTerm1;
    
    /** 決算期2 */
    private String uaiCorpSettlementTerm2;
    
    /** 口座開設年月日 */
    private String uaiOpenAcctDate;
    
    /** ジュニアNISA払出制限期間終了日 */
    private String uaiJnisaSeigenShuryoYmd;
}
