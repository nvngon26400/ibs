package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 共通関数FCT024 SQL003リクエストモデル
 *
 * @author SCSK
 *
 */
@Data
public class Fct024Sql003RequestModel extends ModelBase {
    
    //扱者コード
    private String dealerNumber;
    
    //ファンドタイプ
    private String fundType;
    
    //協会コード
    private String kyoukaiCd;
    
}
