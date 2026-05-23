package com.sbisec.helios.ap.bizcommon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001ResponseModel;

@Mapper
public interface Fct007Mapper {
    /**
     * SQL001証券営業日付取得

     * @param fct007Sql001RequestModel
     * @return
     */
    public Fct007Sql001ResponseModel getDesignatedDate(
            @Param("fct007Sql001RequestModel") Fct007Sql001RequestModel fct007Sql001RequestModel);

}
