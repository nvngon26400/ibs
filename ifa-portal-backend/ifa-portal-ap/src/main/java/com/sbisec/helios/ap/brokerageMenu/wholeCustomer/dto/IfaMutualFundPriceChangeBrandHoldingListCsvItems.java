package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaMutualFundPriceChangeBrandHoldingListCsvItems extends ModelBase {
    
    /** シリアルナンバー */
    private static final long serialVersionUID = -4621048495561938359L;
    
    /** 下落率区分 */
    private String declineRateKbn;
    
    /** ステータス区分 */
    private String statusKbn;
    
    /** 対応方法区分 */
    private String methodsKbn;
    
    /** その他内容区分 */
    private String otherContentsKbn;
    
    /** その他詳細 */
    private String otherDetails;
    
    /** 顧客対応日 */
    private String customerSupportDate;
    
    /** 対応完了確認日 */
    private String completionConfirmationDate;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 取引コース */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字) */
    private String nameKanji;
    
    /** 顧客名_姓名(カナ) */
    private String nameKana;
    
    /** 扱者コード */
    private String dealerNumber;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String branchName;
    
    /** 仲介業支店コード */
    private String brokerBranchCode;
    
    /** 仲介業者支店名 */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード */
    private String brokerEmployeeCode;
    
    /** 仲介業者担当者名 */
    private String brokerChargeName;
    
    /** 投信協会コード */
    private String toushinKyoukaiCode;
    
    /** 銘柄名 */
    private String toushinBrandName;
    
    /** 基準日（From） */
    private String baseDateFrom;
    
    /** 基準価額（From） */
    private String basePriceFrom;
    
    /** 基準日（To） */
    private String baseDateTo;
    
    /** 基準価額（To） */
    private String basePriceTo;
    
    /** 前日比 */
    private String zenjitsuHi;
    
    /** 下落率 */
    private String declineRate;
    
}
