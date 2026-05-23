package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 国内株式情報取得 PTS銘柄マスタ取得 リクエストDTO

 * @author SCSK

 * @see ORACLE - > ETI -> CORDYS -> PTS_BRAND_MASTER(PTS銘柄マスタ).xlsx
 */
@Data
public class Fct027Sql003RequestModel extends ModelBase {
    
    // 銘柄コード
    private String brandCodeFirst;

}
