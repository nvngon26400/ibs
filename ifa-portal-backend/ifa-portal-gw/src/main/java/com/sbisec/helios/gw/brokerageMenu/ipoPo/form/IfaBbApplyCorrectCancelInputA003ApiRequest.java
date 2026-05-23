package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectCancelInputA003ApiRequest {

    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(max = 8, message = "ブックビルディング申込期間（開始）")
    private String bookBuildingPresentationFrom;
    
    /** 訂正前数量. */
    @NotEmpty(message = "訂正前数量")
    private String quantityBeforeCorrect;
    
    /** 訂正前成行. */
    @NotEmpty(message = "訂正前成行")
    private String marketOrderBeforeCorrect;
    
    /** 訂正前価格. */
    @NotEmpty(message = "訂正前価格")
    private String priceBeforeCorrect;
    
    /** 訂正前ディスカウント率. */
    @NotEmpty(message = "訂正前ディスカウント率")
    private String discountRateBeforeCorrect;
    
    /** 訂正前投資家属性順序. */
    @NotEmpty(message = "訂正前投資家属性順序")
    private String investorAttributeValueBeforeCorrect;
    
    /** 訂正前投資家属性名. */
    @NotEmpty(message = "訂正前投資家属性名")
    private String investorAttributeNameBeforeCorrect;
    
    /** 訂正前裁量希望数量. */
    @NotEmpty(message = "訂正前裁量希望数量")
    private String discretionQuantityBeforeCorrect;
    
    /** 訂正前裁量選定理由. */
    @NotEmpty(message = "訂正前裁量選定理由")
    private String selectReasonBeforeCorrect;
    
    /** 訂正前備考. */
    @NotEmpty(message = "訂正前備考")
    private String bbRemarkBeforeCorrect;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 売買単位区分（半角英数字）. */
    @NotEmpty(message = "売買単位区分")
    @Size(min = 1, max = 1, message = "売買単位区分")
    private String sellBuyUnitType;
    
    /** 発行価格区分. */
    @NotEmpty(message = "発行価格区分")
    private String issuePriceType;
    
    /** 顧客名（漢字）（全角半角）. */
    @Size(max = 72, message = "顧客名（漢字）")
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    @Size(max = 72, message = "顧客名（カナ）")
    private String customerNameKana;
    
    private String customerCode;

}
