
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.common.annotation.dao.EtintraMapper;

import org.apache.ibatis.annotations.Param;

@EtintraMapper
public interface BBAcceptMapper {

    public int getBBAcceptInfoCount(@Param("bbProductCode") String bbProductCode,
            @Param("bbPresentationFrom") String bbPresentationFrom, @Param("bbBranchCode") String bbBranchCode,
            @Param("bbAccountNumber") String bbAccountNumber) throws Exception;
    
    public int insertUploadIpopoBBApplyInfoToBBAccept(@Param("insertList") List<IpopoUploadBBAcceptModel> insertList);
}
