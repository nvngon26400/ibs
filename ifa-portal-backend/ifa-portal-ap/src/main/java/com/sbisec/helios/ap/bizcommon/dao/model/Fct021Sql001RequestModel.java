package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取引制限マトリクス取得
 *
 * @author SCSK
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fct021Sql001RequestModel extends ModelBase {
    
    /** 証券金銭種別 */
    private String productCd;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 国籍コード */
    private String countryCd;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 選択市場 */
    private String tradeRestrictChkMarket;
    
    /** 一日信用フラグ */
    private String oneDayCreditFlag;
    
}
