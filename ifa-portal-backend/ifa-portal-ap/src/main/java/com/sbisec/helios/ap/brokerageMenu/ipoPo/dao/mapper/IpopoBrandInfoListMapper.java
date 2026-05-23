
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;

@Mapper
public interface IpopoBrandInfoListMapper {
    
    public List<IpopoBrandInfoListModel> getIpopoBrandInfoList(@Param("querySizeLimit") String querySizeLimit)
            throws Exception;
}
