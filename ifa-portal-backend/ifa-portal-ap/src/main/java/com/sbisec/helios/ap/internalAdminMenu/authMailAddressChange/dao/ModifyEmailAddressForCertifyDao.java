package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;

public interface ModifyEmailAddressForCertifyDao {

    /**
     * 検索用
     * @throws Exception
     */
    public DataList<ModifyEmailAddressForCertifyModel> getCertifyInfoList(
            String privId,
            String userId,
            String brokerOrBranchName,
            String employeeName,
            List<?> brokerChargeList) throws Exception;

    /**
     * 更新用
     * @return
     * @throws Exception
     */
    public int updateMailInfo(ModifyEmailAddressForCertifyModel model) throws Exception;

    /**
     * チェック用
     * @throws Exception
     */    
    public Integer existCnt(String userId) throws Exception;
}
