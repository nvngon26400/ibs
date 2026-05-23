package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数：FCT022
 * FCT022Mapper
 *
 * @author 陳
 */
@Mapper
public interface Fct022Mapper {
    
    /**
     * 国内投信販売手数料取得
     *
     * @return　SQL002レスポンスモデルリスト
     */
    public List<Fct022Sql002ResponseModel> getFct022Sql002(@Param("sql002Req") Fct022Sql002RequestModel sql002Req);
    
    /**
     * 国内投信基準価額取得
     *
     * @return　SQL001レスポンスモデルリスト
     */
    public List<Fct022Sql001ResponseModel> getFct022Sql001(@Param("sql001Req") Fct022Sql001RequestModel sql001Req);
    
    /**
     * 扱者個別国内投信販売手数料取得
     *
     * @return　SQL001レスポンスモデルリスト
     */
    public List<Fct022Sql003ResponseModel> getFct022Sql003(@Param("sql003Req") Fct022Sql003RequestModel sql003Req);
}
