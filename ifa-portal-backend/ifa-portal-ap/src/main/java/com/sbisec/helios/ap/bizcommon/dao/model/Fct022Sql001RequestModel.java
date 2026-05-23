package com.sbisec.helios.ap.bizcommon.dao.model;

import java.util.List;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * FCT002.sql001 国内投信基準価額取得
 *
 * @author 陳
 *
 */
@Data
public class Fct022Sql001RequestModel extends ModelBase {
    
    //協会コード
    private List<String> kyoukaiCd;
    
}
