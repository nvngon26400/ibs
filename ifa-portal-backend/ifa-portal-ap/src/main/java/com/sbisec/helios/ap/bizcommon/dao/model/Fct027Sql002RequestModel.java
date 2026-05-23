package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 国内株式情報取得 株式詳細マスタ取得 リクエストDTO

 * @author SCSK

 * @see ORACLE - > ET10 -> ETRADE -> STOCK_DETAIL_MASTER(株式詳細マスタ).xlsx
 */
@Data
public class Fct027Sql002RequestModel extends ModelBase {
    
    // 銘柄コード
    private String brandCodeFirst;

}
