package com.sbisec.helios.ap.bizcommon.dao.mapper;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数：FCT024
 * FCT024Mapper
 *
 * @author 陳
 */
@Mapper
public interface Fct024Mapper {
    
    /**
     * 国内投信販売手数料取得
     *
     * @param sql002Req sql002リクエスト
     * @return sql002レスポンス
     */
    public Fct024Sql002ResponseModel getFct024Sql002(@Param("sql002Req") Fct024Sql002RequestModel sql002Req);
    
    /**
     * 国内投信基準価額取得
     *
     * @param sql001Req sql001リクエスト
     * @return sql001レスポンス
     */
    public Fct024Sql001ResponseModel getFct024Sql001(@Param("sql001Req") Fct024Sql001RequestModel sql001Req);
    
    /**
     * 扱者個別国内投信販売手数料取得
     *
     * @param sql003Req sql003リクエスト
     * @return sql003レスポンス
     */
    public Fct024Sql003ResponseModel getFct024Sql003(@Param("sql003Req") Fct024Sql003RequestModel sql003Req);
    
}
