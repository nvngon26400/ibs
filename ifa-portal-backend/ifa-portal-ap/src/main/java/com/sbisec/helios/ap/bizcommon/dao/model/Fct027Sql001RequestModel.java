package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 国内株式情報取得 銘柄属性情報取得 リクエストDTO

 * @author SCSK

 * @see ORACLE - > ETI -> ETINTRA -> STAR_BRAND_MASTER(銘柄属性情報).xlsx
 *  
 */
@Data
public class Fct027Sql001RequestModel extends ModelBase {

    // 銘柄コード 先頭4桁
    private String brandCodeFirst;
    
    // 銘柄コード 末尾1桁
    private String brandCodeLast;

}
