package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 国内株式情報取得 株式詳細マスタ取得 リクエストDTO

 * @author SCSK

 * @see ORACLE - > ET10 -> ETRADE -> STOCK_DETAIL_MASTER(株式詳細マスタ).xlsx
 */
@Data
public class Fct027Sql002ResponseModel extends ModelBase {
    
    // 規制銘柄区分
    private String regKbn;
    
    // 東証一般信用区分
    private String mktIppanLoanKbnTky;

    // 名証一般信用区分
    private String mktIppanLoanKbnNgy;
    
    // 福証一般信用区分
    private String mktIppanLoanKbnFko;
    
    // 札証一般信用区分
    private String mktIppanLoanKbnSpr;
        
    // SOR取扱区分
    private String sorServiceKbn;
    
    // プレミアム空売り区分
    private String premiumShortSellingKbn;
    
    // 売買単位
    private String bsUnit;
    
    //東証貸借区分
    private String mktLoanKbnTky;
    
}
