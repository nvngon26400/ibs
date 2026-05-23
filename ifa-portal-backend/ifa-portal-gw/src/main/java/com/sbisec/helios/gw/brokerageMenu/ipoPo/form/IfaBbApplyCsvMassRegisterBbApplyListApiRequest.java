package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

/**
 * 画面ID：SUB0204_01-03_1
 * 画面名：BB申込(一括登録)
 *
 * @author SCSK
 *
 */
@Data
public class IfaBbApplyCsvMassRegisterBbApplyListApiRequest {
    
    /**銘柄コード */
    private String brandCode;
    
    /**銘柄名 */
    private String brandName;
    
    /**部店 */
    private String butenCode;
    
    /**口座番号 */
    private String accountNumber;
    
    /**顧客名 */
    private String customerName;
    
    /**希望株数 */
    private String bbQuantity;
    
    /**申込価格 */
    private String bbPrice;
    
    /**発行価格区分 */
    private String bbGestureValue;
    
    /**投資家属性 */
    private String bbInvestorAttName;
    
    /**投資家属性順序 */
    private String bbSeq;
    
    /**裁量希望株数 */
    private String quantitySairyou;
    
    /**裁量選定理由 */
    private String choseReason;
    
    /**備考 */
    private String bbRemark;
    
    /**勧誘区分 */
    private String kanyuKbn;
    
    /**受注方法 */
    private String receiveOrderType;
    
    /**チェック結果 */
    private String checkResult;
}