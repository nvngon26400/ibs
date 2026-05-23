package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 共通関数FCT024 SQL001レスポンスモデル
 *
 * @author SCSK
 *
 */
@Data
public class Fct024Sql001ResponseModel extends ModelBase {
    
    //協会コード
    private String kyoukaiCd;
    
    //基準価額
    private BigDecimal basePrice;
    
    //基準価額日付
    private String basePriceDate;
    
    //前日比
    private BigDecimal diff;
    
    //前日基準価額
    private BigDecimal zenjitsuKijunkakaku;
    
    //純資産
    private BigDecimal junshisan;
    
    //52週高値
    private BigDecimal w52Takane;
    
    //52週高値日付
    private String w52Takanedate;
    
    //52週安値
    private BigDecimal w52Yasune;
    
    //52週安値日付
    private String w52Yasunedate;
    
}
