package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * FCT021 取引制限チェックMapper
 *
 * @author SCSK
 *
 */
@Mapper
public interface Fct021Mapper {
    
    public List<Fct021Sql001ResponseModel> getFct021Sql001(
            @Param("fct021Sql001RequestModel") Fct021Sql001RequestModel fct021Sql001RequestModel);
    
    public List<Fct021Sql002ResponseModel> getFct021Sql002(
            @Param("fct021Sql002RequestModel") Fct021Sql002RequestModel fct021Sql002RequestModel);
    
}
