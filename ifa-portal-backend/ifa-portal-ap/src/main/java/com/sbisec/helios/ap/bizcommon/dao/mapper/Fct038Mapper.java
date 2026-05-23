package com.sbisec.helios.ap.bizcommon.dao.mapper;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数：FCT038
 * CSVダウンロードMAX件数取得
 *
 * @author SCSK
 */
@Mapper
public interface Fct038Mapper {
    
    /**
     * SQL001CSVダウンロードMAX件数取得
     *
     * @param fct038Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception CSVダウンロードMAX件数取得に例外が発生した場合
     */
    public Fct038Sql001ResponseModel getCsvMax(
            @Param("fct038Sql001RequestModel") Fct038Sql001RequestModel fct038Sql001RequestModel) throws Exception;
    
}
