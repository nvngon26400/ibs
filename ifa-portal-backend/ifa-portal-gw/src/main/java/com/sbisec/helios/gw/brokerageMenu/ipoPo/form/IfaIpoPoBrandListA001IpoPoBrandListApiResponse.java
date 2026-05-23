package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.math.BigDecimal;

import lombok.Data;

/**
 * IPOPO銘柄一覧 A001レスポンス（IPO/PO銘柄一覧）
 *
 * @author SCSK
 */
@Data
public class IfaIpoPoBrandListA001IpoPoBrandListApiResponse {
    
    /** IPO／PO区分 */
    private String bbIpoPoKbn;
    
    /** 銘柄コード */
    private String bbProductCode;
    
    /** 1付き */
    private String attachedBrand;
    
    /** たばこ開示フラグ */
    private String cigaretteShowFlag;
    
    /** 電子交付のみフラグ */
    private String edelivOnlyFlag;    
    
    /** 期間変更フラグ */
    private String changeBbPeriodFlag;
    
    /** 価格変更フラグ */
    private String changePriceFlag;
    
    /** 銘柄名 */
    private String bbProductName;
    
    /** 配分上限株数 */
    private BigDecimal maxAllocation;
    
    /** 売買単位 */
    private String bbStock;
    
    /** BB申込期間 */
    private String bbPresentation;
    
    /** ステータス */
    private String status;
    
    /** 募集期間 */
    private String bbPeriod;
    
    /** 入金予定日 */
    private String paymentDate;
    
    /** 売買単位区分 */
    private String bbUnitKbn;
    
    /** ブックビルディング申込期間（開始） */
    private String bbPresentationFrom;

}
