package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*
* @author BASE李
*
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaBbApplyListA004aResponseDtoBbApplyList extends ModelBase {

    /**
     * シリアルナンバー
     */
    private static final long serialVersionUID = 7642254504976949335L;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名. */
    private String brokerName;

    /** 支店コード. */
    private String brokerBranchCode;

    /** 支店名. */
    private String brokerBranchName;
    
    /**業員コード（半角英数字）. */
    private String brokerChargeCode;

    /** 営業員名（全角半角）. */
    private String employeeName;

    /** 部店（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 取引コース（半角英数字）. */
    private String course;
    
    /** 投資家属性 */
    private String investorAttributeName;

    /** BB申込株数（数値(整数)）. */
    private String bbQuantity;  
    
    /** 申込価格. */
    private String bbPrice;
    
    /** 裁量希望株数. */
    private String quantitySairyou;
    
    /** 抽選株数. */
    private String bbQuantityAlloc;
    
    /** 抽選結果. */
    private String lotteryResult;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 注文株数 */
    private String orderQuantity;
    
    /** 預り区分 */
    private String depositType;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** ワーニング申請チェック */
    private String warningApplyArranged;
    
    /** 申込日時. */
    private String bbCreateDate;
    
    /** 申込者. */
    private String bbCreateUserName;

    /** セクション名. */
    private String sectionName;
    
    /** 電子交付同意 */
    private String edelivAgreement;
    
    /** 目論見書閲覧. */
    private String readTime;
    
    /** 備考（全角半角）. */
    private String bbRemark;
}
