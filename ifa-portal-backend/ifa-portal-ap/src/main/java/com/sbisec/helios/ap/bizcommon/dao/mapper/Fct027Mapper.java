package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003ResponseModel;

/**
 * 国内株式情報取得 銘柄属性情報取得 リクエストDTO

 * @author SCSK

 */

@Mapper
public interface Fct027Mapper {
    

    public List<Fct027Sql001ResponseModel> getFct027Sql001(@Param("Fct027Sql001RequestModel")
                Fct027Sql001RequestModel fct027Sql001RequestModel);

    public List<Fct027Sql002ResponseModel> getFct027Sql002(@Param("Fct027Sql002RequestModel")
                Fct027Sql002RequestModel fct027Sql002RequestModel);

    public List<Fct027Sql003ResponseModel> getFct027Sql003(@Param("Fct027Sql003RequestModel")
               Fct027Sql003RequestModel fct027Sql003RequestModel);
}
