package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.service;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;
import com.sbisec.helios.ap.service.Service;

public interface ModifyEmailAddressForCertifyService extends Service {

    /**
     * 検索用
     * @throws Exception
     */
    public DataList<ModifyEmailAddressForCertifyModel> getCertifyInfoList(
            String privId,
            String userId,
            String brokerOrBranchName,
            String employeeName,
            List<?> brokerChargeList)
            throws Exception;

    /**
     * 更新用
     * @throws Exception
     */
    public int updateMailInfo( String userId, String mailAddr, String sysUserid) throws Exception;
    
    /**
     * チェック用
     * @throws Exception
     */
    public Integer existCnt(String userId) throws Exception;
    
    /**
     * FCT030：参照可能な仲介業者コードと営業員コードを取得
     * @throws Exception
     */
    public OutputFct030Dto getFct030(String dammy) throws Exception;
    
}
