package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 銘柄別建玉一覧A001リスポンス
 *
 * @author SCSK
 */
@Data
public class IfaBrandPositionListA001ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 建玉区分（新規売買区分）. */
    private String newCreditOrderType;
    
    /** 建玉区分（弁済期限）. */
    private String specificPositionTypePaymentDeadline;
    
    /** 新規売買区分（全角半角）. */
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 検索結果件数（数値(整数)）. */
    private String searchResultCount;
    
    /** 媒介可否リスト. */
    private List<IntermediaryValue> intermediaryValueList;
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 東証リスト. */
    private List<TokyoSecurity> tokyoSecurityList;
    
    /** PTSリスト. */
    private List<Pts> ptsList;
    
    /** 銘柄建玉明細リスト. */
    private List<BrandPositionDetail> brandPositionDetailList;
    
    /** 建市場_東証フラグ. */
    private String tokyoSecurityFlag;
    
    /** 建市場_PTSフラグ. */
    private String ptsFlag;
    
}
