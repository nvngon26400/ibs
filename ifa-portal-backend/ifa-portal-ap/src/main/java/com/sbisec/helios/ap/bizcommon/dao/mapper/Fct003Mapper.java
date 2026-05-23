package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002ResponseModel;

/**
 * @author 鄒
 *
 */
@Mapper
public interface Fct003Mapper {
    
    public List<Fct003Sql001ResponseModel> getFct003Sql001(
            @Param("fct003Sql001RequestModel") Fct003Sql001RequestModel fct003Sql001RequestModel);
    
    public List<Fct003Sql002ResponseModel> getFct003Sql002(
            @Param("fct003Sql002RequestModel") Fct003Sql002RequestModel fct003Sql002RequestModel);
}
