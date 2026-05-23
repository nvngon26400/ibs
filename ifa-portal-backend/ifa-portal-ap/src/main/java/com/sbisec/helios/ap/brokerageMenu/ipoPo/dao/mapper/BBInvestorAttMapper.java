
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BBInvestorAttMapper {

    public List<BBInvestorAttModel> getBBInvestorAttInfoList(@Param("bbProductCode") String bbProductCode,
            @Param("bbPresentationFrom") String bbPresentationFrom) throws Exception;
}