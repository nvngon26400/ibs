package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報SQL011応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql011ResponseModel {
    
    /** ファンドタイプ（半角英数字）. */
    private String fcmType;
    
    /** 協会コード（全角半角）. */
    private String fcmCode;
    
    /** 販売手数料上限1. */
    private String fcmUpper1;
    
    /** 販売手数料率１. */
    private String fcmRitsu1;
    
    /** 販売手数料上限2. */
    private String fcmUpper2;
    
    /** 販売手数料率2. */
    private String fcmRitsu2;
    
    /** 販売手数料上限3. */
    private String fcmUpper3;
    
    /** 販売手数料率3. */
    private String fcmRitsu3;
    
    /** 販売手数料上限4. */
    private String fcmUpper4;
    
    /** 販売手数料率4. */
    private String fcmRitsu4;
    
    /** 販売手数料上限5. */
    private String fcmUpper5;
    
    /** 販売手数料率5. */
    private String fcmRitsu5;
    
    /** 販売手数料上限6. */
    private String fcmUpper6;
    
    /** 販売手数料率6. */
    private String fcmRitsu6;
    
    /** 販売手数料上限7. */
    private String fcmUpper7;
    
    /** 販売手数料率7. */
    private String fcmRitsu7;
    
    /** 手数料区分（半角英数字）. */
    private String fcmKubun;
    
    /** 販売手数料上限1(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou1;
    
    /** 販売手数料率1(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou1;
    
    /** 販売手数料上限2(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou2;
    
    /** 販売手数料率2(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou2;
    
    /** 販売手数料上限3(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou3;
    
    /** 販売手数料率3(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou3;
    
    /** 販売手数料上限4(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou4;
    
    /** 販売手数料率4(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou4;
    
    /** 販売手数料上限5(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou5;
    
    /** 販売手数料率5(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou5;
    
    /** 販売手数料上限6(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou6;
    
    /** 販売手数料率6(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou6;
    
    /** 販売手数料上限7(総合NISA・成長投資枠). */
    private String fcmHUpperSeichou7;
    
    /** 販売手数料率7(総合NISA・成長投資枠). */
    private String fcmHRitsuSeichou7;
    
    /** 販売手数料個別設定有無(総合NISA・成長投資枠). */
    private String fcmHSettingSeichou;
    
}
