package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * SQL001.英文開示銘柄取得
 *
 * @author 鄒
 *
 */
@Mapper
public interface Fct029Mapper {
    
    public Fct029Sql001ResponseModel getFct029Sql001(
            @Param("fct029Sql001RequestModel") Fct029Sql001RequestModel fct029Sql001RequestModel);

}
