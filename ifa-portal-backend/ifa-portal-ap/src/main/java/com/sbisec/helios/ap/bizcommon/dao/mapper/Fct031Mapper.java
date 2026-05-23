package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数：FCT031
 * 顧客情報取得
 *
 * @author SCSK
 */
@Mapper
public interface Fct031Mapper {
    
    /**
     * SQL001顧客情報取得
     *
     * @param fct031Sql001RequestModel リクエスト
     * @return レスポンス
     */
    public List<Fct031Sql001ResponseModel> getCustomerInfo(
            @Param("fct031Sql001RequestModel") Fct031Sql001RequestModel fct031Sql001RequestModel) throws Exception;
    
}
