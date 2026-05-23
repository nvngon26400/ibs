package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 鄒
 *
 */
@Mapper
public interface Fct025Mapper {
    
    public List<Fct025Sql001ResponseModel> getFct025Sql001(
            @Param("fct025Sql001RequestModel") Fct025Sql001RequestModel fct025Sql001RequestModel);
    
    public List<Fct025Sql002ResponseModel> getFct025Sql002(
            @Param("fct025Sql002RequestModel") Fct025Sql002RequestModel fct025Sql002RequestModel);
}
