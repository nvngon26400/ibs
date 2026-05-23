package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import java.util.List;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA002ResponseDto {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 顧客名 */
    private String customerName;
    /** 口座開設年月日 */
    private String openAcctDate;
    /** 投資方針 */
    private String investmentPolicyType;
    /** 職業 */
    private String profession;
    /** 投資経験年数(株式現物) */
    private String stockSpotInvestExperienceYears;
    /** 勤務先名 */
    private String officeName;
    /** 金融資産 */
    private String financialAssets;
    /** コンプラランク */
    private String complianceRank;
    /** 電子交付同意 */
    private String edelivAgreementDate;
    /** 法人区分 */
    private String corporationType;
    /** 金融資産区分 */
    private String financialAssetsType;
    /** 本年の年間裁量配分割当回数 */
    private String discretionAlloCount;
    /** 本年の年間裁量配分可能回数 */
    private String discretionAllocateAbleTimes;
    /** 預り資産額 */
    private String depositAssetAmount;
    /** 銘柄プルダウンリスト */
    private List<String> brandPullDownList;
    /** 銘柄コードリスト */
    private List<String> brandCodeList;
    /** 買付余力 */
    private String buyingPower;
    /** 遷移元画面 */
    private String transitionSourceScreen;
    /** 顧客コード */
    private String customerCode;
    
}
