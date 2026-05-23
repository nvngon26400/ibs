package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;

@Mapper
public interface ModifyEmailAddressForCertifyMapper {

    /**
     * 検索用
     * @throws Exception
     */
    public List<ModifyEmailAddressForCertifyModel> getCertifyInfoList(
            @Param("privId")              String privId,
            @Param("userId")              String userId,
            @Param("brokerOrBranchName")  String brokerOrBranchName,
            @Param("employeeName")        String employeeName,
            @Param("brokerChargeList")        List<?> brokerChargeList,
            @Param("rowNum")              String rownum
            ) throws Exception;

    /**
     * 更新用
     * @throws Exception
     */
    public int updateMailInfo(@Param("model") ModifyEmailAddressForCertifyModel model) throws Exception;
    
    /**
     * チェック用
     * @throws Exception
     */
    public Integer existCnt(@Param("userId") String userId) throws Exception;
}
