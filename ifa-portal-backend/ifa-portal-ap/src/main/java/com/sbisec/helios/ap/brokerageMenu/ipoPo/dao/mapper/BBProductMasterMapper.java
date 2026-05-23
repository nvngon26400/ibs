
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;

@Mapper
public interface BBProductMasterMapper {
    
    public BBProductMasterModel getBBProductMasterInfo(@Param("bbProductCode") String bbProductCode,
            @Param("bbPresentationFrom") String bbPresentationFrom) throws Exception;
}
