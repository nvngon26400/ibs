package com.sbisec.helios.ap.bizcommon.dao.mapper;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数Mapper:FCT023
 *
 * @author 陳
 * 
 */
@Mapper
public interface Fct023Mapper {
    
    /**
     * 国内投信販売手数料取得
     *
     * @param sql001Req NRIコード
     * @return 指定銘柄の各種情報
     */
    Fct023Sql001ResponseModel getFct023Sql001(@Param("sql001Req") Fct023Sql001RequestModel sql001Req);
}
