package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 国内株式情報取得 PTS銘柄マスタ取得 レスポンスDTO

 * @author SCSK

 * @see ORACLE - > ETI -> CORDYS -> PTS_BRAND_MASTER(PTS銘柄マスタ).xlsx
 */
@Data
public class Fct027Sql003ResponseModel extends ModelBase {
    
    // PTS貸借区分
    private String mktLoanKbnPts;
    
    // PTS一般信用区分
    private String mktIppanLoanKbnPts;
    
}
