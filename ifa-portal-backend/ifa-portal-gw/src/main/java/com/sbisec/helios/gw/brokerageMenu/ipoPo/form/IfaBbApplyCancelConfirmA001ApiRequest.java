package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE 李
 2024/05/14 新規作成
 */
@Data
public class IfaBbApplyCancelConfirmA001ApiRequest {

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

    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;

    /** 成行. */
    @NotEmpty(message = "成行")
    private String marketOrder;

    /** 価格（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "価格")
    @NotEmpty(message = "価格")
    @Size(max = 22, message = "価格")
    private String price;

    /** ディスカウント率（数値(小数)）. */
    @Digits(integer = 3, fraction = 4, message = "ディスカウント率")
    @NotEmpty(message = "ディスカウント率")
    @Size(max = 8, message = "ディスカウント率")
    private String discountRate;

    /** 投資家属性順序（半角英数字）. */
    @NotEmpty(message = "投資家属性順序")
    @Size(min = 1, max = 1, message = "投資家属性順序")
    private String investorAttributeValue;

    /** 投資家属性名. */
    @NotEmpty(message = "投資家属性名")
    private String investorAttributeName;

    /** 備考（全角半角）. */
    @NotEmpty(message = "備考")
    @Size(max = 200, message = "備考")
    private String bbRemark;

    /** 裁量希望数量. */
    @NotEmpty(message = "裁量希望数量")
    private String discretionQuantity;

    /** 選定理由. */
    @NotEmpty(message = "選定理由")
    private String selectReason;

    /** 顧客名（漢字）（全角半角）. */
    @NotEmpty(message = "顧客名（漢字）")
    @Size(max = 72, message = "顧客名（漢字）")
    private String customerNameKanji;

    /** 顧客名（カナ）（全角半角）. */
    @NotEmpty(message = "顧客名（カナ）")
    @Size(max = 72, message = "顧客名（カナ）")
    private String customerNameKana;

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

    /** 注意情報レベル. */
    @NotEmpty(message = "注意情報レベル")
    private String noticeInfoLevel;
    
    private String customerCode;

}
