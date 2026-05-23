package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報 SQL010応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql010ResponseModel {
    
    /** ファンドタイプ（半角英数字）. */
    private String mFType;
    
    /** ファンド正式名. */
    private String mFName;
    
    /** 委託会社コード. */
    private String mFItakuCmp;
    
    /** 一口当たり額面. */
    private String mFGakumen;
    
    /** 信託期間終了年月日. */
    private String mFEndDate;
    
    /** 償還年月日. */
    private String mFSyokanDate;
    
    /** 注文締切時間. */
    private String mFShimekiri;
    
    /** 手数料区分（半角英数字）. */
    private String mFHKubun;
    
    /** 販売手数料単位口数. */
    private String mFHTanikuchi;
    
    /** 販売最低口数. */
    private String mFHMinkuchi;
    
    /** 販売単位口数. */
    private String mFHKuchisu;
    
    /** 販売最小単位金額(2回目以降). */
    private String mFHMintani2;
    
    /** 販売売買単位金額(2回目以降). */
    private String mFHBstani2;
    
    /** 販売手数料上限1. */
    private String mFHUpper1;
    
    /** 販売手数料額1. */
    private String mFHGaku1;
    
    /** 販売手数料率１. */
    private String mFHRitsu1;
    
    /** 販売手数料上限2. */
    private String mFHUpper2;
    
    /** 販売手数料額2. */
    private String mFHGaku2;
    
    /** 販売手数料率2. */
    private String mFHRitsu2;
    
    /** 販売手数料上限3. */
    private String mFHUpper3;
    
    /** 販売手数料額3. */
    private String mFHGaku3;
    
    /** 販売手数料率3. */
    private String mFHRitsu3;
    
    /** 販売手数料上限4. */
    private String mFHUpper4;
    
    /** 販売手数料額4. */
    private String mFHGaku4;
    
    /** 販売手数料率4. */
    private String mFHRitsu4;
    
    /** 販売手数料上限5. */
    private String mFHUpper5;
    
    /** 販売手数料額5. */
    private String mFHGaku5;
    
    /** 販売手数料率5. */
    private String mFHRitsu5;
    
    /** 販売手数料上限6. */
    private String mFHUpper6;
    
    /** 販売手数料額6. */
    private String mFHGaku6;
    
    /** 販売手数料率6. */
    private String mFHRitsu6;
    
    /** 販売手数料上限7. */
    private String mFHUpper7;
    
    /** 販売手数料額7. */
    private String mFHGaku7;
    
    /** 販売手数料率7. */
    private String mFHRitsu7;
    
    /** 解約最小単位口数. */
    private String mFKMinkuchi;
    
    /** 解約売買単位口数. */
    private String mFKKuchisu;
    
    /** 解約最小単位金額(2回目以降). */
    private String mFKMintani2;
    
    /** 解約売買単位金額(2回目以降). */
    private String mFKBstani2;
    
    /** 約定日区分. */
    private String mFYkujyokubun;
    
    /** 受渡日区分. */
    private String mFUkewatashi;
    
    /** 償還優遇率・分子. */
    private String yuguRituBunsi;
    
    /** 積立買付可否区分. */
    private String mFTumitateKubun;
    
    /** 定期売却可否区分. */
    private String mFTeikiSellKubun;
    
    /** 再投資区分. */
    private String mFSaitoushiKubun;
    
    /** 販売手数料上限1(総合NISA・成長投資枠). */
    private String mFHUpperSeichou1;
    
    /** 販売手数料額1(総合NISA・成長投資枠). */
    private String mFHGakuSeichou1;
    
    /** 販売手数料率1(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou1;
    
    /** 販売手数料上限2(総合NISA・成長投資枠). */
    private String mFHUpperSeichou2;
    
    /** 販売手数料額2(総合NISA・成長投資枠). */
    private String mFHGakuSeichou2;
    
    /** 販売手数料率2(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou2;
    
    /** 販売手数料上限3(総合NISA・成長投資枠). */
    private String mFHUpperSeichou3;
    
    /** 販売手数料額3(総合NISA・成長投資枠). */
    private String mFHGakuSeichou3;
    
    /** 販売手数料率3(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou3;
    
    /** 販売手数料上限4(総合NISA・成長投資枠). */
    private String mFHUpperSeichou4;
    
    /** 販売手数料額4(総合NISA・成長投資枠). */
    private String mFHGakuSeichou4;
    
    /** 販売手数料率4(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou4;
    
    /** 販売手数料上限5(総合NISA・成長投資枠). */
    private String mFHUpperSeichou5;
    
    /** 販売手数料額5(総合NISA・成長投資枠). */
    private String mFHGakuSeichou5;
    
    /** 販売手数料率5(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou5;
    
    /** 販売手数料上限6(総合NISA・成長投資枠). */
    private String mFHUpperSeichou6;
    
    /** 販売手数料額6(総合NISA・成長投資枠). */
    private String mFHGakuSeichou6;
    
    /** 販売手数料率6(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou6;
    
    /** 販売手数料上限7(総合NISA・成長投資枠). */
    private String mFHUpperSeichou7;
    
    /** 販売手数料額7(総合NISA・成長投資枠). */
    private String mFHGakuSeichou7;
    
    /** 販売手数料率7(総合NISA・成長投資枠). */
    private String mFHRitsuSeichou7;
    
    /** 乗換優遇率・分母(総合NISA・成長投資枠). */
    private String yuguRituBunboSeichou;
    
    /** 乗換優遇率・分子(総合NISA・成長投資枠). */
    private String yuguRituBunsiSeichou;
    
    /** 総合NISA・成長投資枠取扱区分. */
    private String mFSougoSeichouServiceKbn;
    
    /** 総合NISA・成長投資枠手数料個別設定有無. */
    private String mFSeichouTesuryoSetting;
    
}
