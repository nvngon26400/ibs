package com.sbisec.helios.ap.bizcommon.dao.mapper;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数：FCT039
 * ポイント照会
 *
 * @author SCSK
 */
@Mapper
public interface Fct039Mapper {
    
    /**
     * ポイント関連項目表示可否取得
     *
     * @param fct039Sql001RequestModel リクエスト
     * @return レスポンス
     */
    public Fct039Sql001ResponseModel getPointDisplayPermission(
            @Param("fct039Sql001RequestModel") Fct039Sql001RequestModel fct039Sql001RequestModel);
    
}
