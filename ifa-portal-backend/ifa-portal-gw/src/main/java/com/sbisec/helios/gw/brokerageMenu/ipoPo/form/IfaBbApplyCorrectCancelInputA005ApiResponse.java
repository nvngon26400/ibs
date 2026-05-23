package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002ResponseModel;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectCancelInputA005ApiResponse {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 成行. */
    private String marketOrder;
    
    /** 価格（数値(小数)）. */
    private String price;
    
    /** ディスカウント率（数値(小数)）. */
    private String discountRate;
    
    /** 投資家属性順序（半角英数字）. */
    private String investorAttributeValue;
    
    /** 投資家属性名. */
    private String investorAttributeName;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** 裁量希望数量. */
    private String discretionQuantity;
    
    /** 選定理由. */
    private String selectReason;
    
    /** 銘柄（全角半角）. */
    private String brand;
    
    /** 銘柄名 */
    private String brandName;

    /** 申込単位. */
    private String applyUnit;

    /** 売買単位（数値(整数)）. */
    private String unit;

    /** 売買単位区分（半角英数字）. */
    private String sellBuyUnitType;
    
    /** 仮条件（全角半角）. */
    private String assumeCondition;
    
    /** ブックビルディング申込期間. */
    private String bookBuildingApplyPeriod;

    /** 抽選日. */
    private String lotDate;
    
    /** 発行価格区分. */
    private String issuePriceType;
    
    /** 価格表示（開始）（数値(整数)）. */
    private String startPriceDisplay;

    /** 価格表示（刻み）. */
    private String priceDisplayTick;

    /** 価格表示（終了）（数値(整数)）. */
    private String finishPriceDisplay;

    /** ディスカウント率（開始）（数値(小数)）. */
    private String startDiscountRate;

    /** ディスカウント率（刻み）. */
    private String discountRateTick;

    /** ディスカウント率（終了）（数値(小数)）. */
    private String finishDiscountRate;
    
    /** 期間変更メッセージ */
    private String periodUpdateMessage;
    
    /** 価格変更メッセージ */
    private String priceUpdateMessage;
    
    /** 顧客名 */
    private String customerName;
    
    /** 口座開設年月日 */
    private String openAcctDate;

    /** 投資方針区分 */
    private String investmentPolicyType;
    
    /** 本年の年間裁量配分割当回数 */
    private String discretionAlloCount;
    
    /** 職業区分 */
    private String professionType;
    
    /** 投資経験年数(株式現物) */
    private String stockSpotInvestExperienceYears;
    
    /** 本年の年間裁量配分可能回数 */
    private String discretionAllocateAbleTimes;
    
    /** 勤務先名 */
    private String officeName;
    
    /** 金融資産区分 */
    private String financialAssetsType;
    
    /** 法人区分 */
    private String corporationType;
    
    /** 買付余力 */
    private String buyingPower;
    
    /** コンプラランク */
    private String complianceRank;
    
    /** 預り資産額 */
    private String depositAssetAmount;
    
    /** 電子承諾日付 */
    private String edelivAgreementDate;
    
    /** 閲覧日時 */
    private String readTime;
    
    /** 投資家属性プルダウンリスト. */
    private List<IfaBbApplyCorrectCancelInputSql002ResponseModel> investorAttributePullDownList;
    
    /** 申込日時 */
    private String bbCreateDate;
    
    /** 訂正前数量. */
    private String quantityBeforeCorrect;
    
    /** 訂正前成行. */
    private String marketOrderBeforeCorrect;
    
    /** 訂正前価格/ディスカウント率. */
    private String priceDiscountRateBeforeCorrect;
    
    /** 訂正前投資家属性順序. */
    private String investorAttributeValueBeforeCorrect;
    
    /** 訂正前投資家属性名. */
    private String investorAttributeNameBeforeCorrect;
    
    /** 訂正前裁量希望数量. */
    private String discretionQuantityBeforeCorrect;
    
    /** 訂正前裁量選定理由. */
    private String selectReasonBeforeCorrect;
    
    /** 訂正前備考. */
    private String bbRemarkBeforeCorrect;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受付方法 */
    private String receiveMethod;
    
    /**　成行活性非活性　*/
    private String marketOrderStrikePrice;
    
    private String customerCode;
}
