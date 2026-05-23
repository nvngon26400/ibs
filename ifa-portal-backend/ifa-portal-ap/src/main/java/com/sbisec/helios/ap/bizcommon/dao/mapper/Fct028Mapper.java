package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001ResponseModel;

/**
 * @author 鄒
 *
 */
@Mapper
public interface Fct028Mapper {
    
    /**
     * 店頭取引注文情報の取得
     */
    public List<Fct028Sql001ResponseModel> getFct028Sql001(
            @Param("fct028Sql001RequestModel") Fct028Sql001RequestModel fct028Sql001RequestModel);
    
}
