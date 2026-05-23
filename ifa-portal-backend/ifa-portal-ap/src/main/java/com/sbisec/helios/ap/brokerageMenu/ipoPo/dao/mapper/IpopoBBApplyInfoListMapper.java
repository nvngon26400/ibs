package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;

@Mapper
public interface IpopoBBApplyInfoListMapper {

    public List<IpopoBBApplyInfoListModel> getIpopoBBApplyInfoList(@Param("querySizeLimit") String querySizeLimit) throws Exception;

    public List<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvDownload() throws Exception;

    public Integer updateIpopoOrderStatus(@Param("statusBefore") String statusBefore,
            @Param("statusAfter") String statusAfter, 
            @Param("userId") String userId,
            @Param("sysId") String sysId,
            @Param("sysDate") String sysDate,
            @Param("minKey") String minKey,
            @Param("maxKey") String maxKey) throws Exception;

    /**
     * 注文状態と時間より、レコードを取得する。
     * @param orderStatus
     * @param sysDate
     * @return
     * @throws Exception
     */
    public Integer getOrderStatusCount(@Param("orderStatus") String orderStatus, @Param("sysDate") String sysDate,
            @Param("minKey") String minKey, @Param("maxKey") String maxKey) throws Exception;
}