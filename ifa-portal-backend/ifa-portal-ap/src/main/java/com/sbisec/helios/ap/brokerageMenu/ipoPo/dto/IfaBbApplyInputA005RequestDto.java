package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA005RequestDto {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 銘柄コード */
    private String brandCode;
    /** ブックビルディング申込期間（開始） */
    private String bookBuildingPresentationFrom;
    /** 数量 */
    private String quantity;
    /** 成行 */
    private String marketOrder;
    /** 価格 */
    private String price;
    /** ディスカウント率 */
    private String discountRate;
    /** 投資家属性順序 */
    private String investorAttributeValue;
    /** 投資家属性名 */
    private String investorAttributeName;
    /** 備考 */
    private String bbRemark;
    /** 裁量希望数量 */
    private String discretionQuantity;
    /** 選定理由 */
    private String selectReason;
    /** 遷移元画面 */
    private String transitionSourceScreen;
    /** 勧誘区分 */
    private String kanyuKbn;
    /** 受注方法 */
    private String receiveMethod;
}
