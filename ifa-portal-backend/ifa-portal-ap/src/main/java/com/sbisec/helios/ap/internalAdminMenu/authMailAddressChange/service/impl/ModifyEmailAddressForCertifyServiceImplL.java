package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao.ModifyEmailAddressForCertifyDao;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.service.ModifyEmailAddressForCertifyService;

@Component(value = "cmpModifyEmailAddressForCertifyService")
public class ModifyEmailAddressForCertifyServiceImplL implements ModifyEmailAddressForCertifyService {

    private static final Logger logger = LoggerFactory.getLogger(ModifyEmailAddressForCertifyServiceImplL.class);

    @Autowired
    private ModifyEmailAddressForCertifyDao dao;
    
    @Autowired
    private Fct030 fct030;

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
            throws Exception {

        if (logger.isDebugEnabled()) logger.debug("ModifyEmailAddressForCertifyServiceImplL.getCertifyInfoList");

        return dao.getCertifyInfoList(privId, userId, brokerOrBranchName, employeeName, brokerChargeList);
    }
    
	/**
	 * 更新用
	 * @throws Exception
	 */
	public int updateMailInfo(String userId, String mailAddr, String sysUserid) throws Exception {
		if (logger.isDebugEnabled()) logger.debug("ModifyEmailAddressForCertifyServiceImplL.updateMailInfo");
		
		ModifyEmailAddressForCertifyModel model = new ModifyEmailAddressForCertifyModel();
		model.setUserId(userId);
		model.setMailAddress(mailAddr);
		model.setUpdateBy(sysUserid);
		return dao.updateMailInfo(model);
	}
    
    /**
     * チェック用
     * @throws Exception
     */
    public Integer existCnt(String userId) throws Exception {
        if (logger.isDebugEnabled()) logger.debug("CancelMultiplyCertifyServiceImplL.existCnt");
        return dao.existCnt(userId);
    }
    
    /**
     * FCT030：参照可能な仲介業者コードと営業員コードを取得
     * @throws Exception
     */
    public OutputFct030Dto getFct030(String dammy) throws Exception {
        if (logger.isDebugEnabled()) logger.debug("CancelMultiplyCertifyServiceImplL.getFct030");
        
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        outputFct030Dto = fct030.getData(new InputFct030Dto());
        
        return outputFct030Dto;
    }

}

