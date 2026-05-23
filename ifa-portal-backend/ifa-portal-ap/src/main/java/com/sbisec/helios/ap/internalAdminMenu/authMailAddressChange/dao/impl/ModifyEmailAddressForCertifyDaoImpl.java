package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao.ModifyEmailAddressForCertifyDao;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.dao.mapper.ModifyEmailAddressForCertifyMapper;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;

@Component
public class ModifyEmailAddressForCertifyDaoImpl extends RowSelectableDao implements ModifyEmailAddressForCertifyDao {

    private static final Logger logger = LoggerFactory.getLogger(ModifyEmailAddressForCertifyDaoImpl.class);

    @Autowired
    private ModifyEmailAddressForCertifyMapper mapper;

    /**
     * 検索用
     * @throws Exception
     */
    public DataList<ModifyEmailAddressForCertifyModel> getCertifyInfoList(
            String privId,
            String userId,
            String brokerOrBranchName,
            String employeeName,
            List<?> brokerChargeList) throws Exception {

        logger.debug("DeliveryHistoryDaoImpl.getCertifyInfoList: userId:" +userId + ", brokerOrBranchName:" + brokerOrBranchName  + ", employeeName:" +  employeeName);

        return getCertifyInfoListInternal(privId, userId, brokerOrBranchName, employeeName, brokerChargeList);
    }

    private DataList<ModifyEmailAddressForCertifyModel> getCertifyInfoListInternal(
            String privId,
            String userId,
            String brokerOrBranchName,
            String employeeName,
            List<?> brokerChargeList) throws Exception {

        int count=0;
        int maxRow =  maxRownum;

        String privIdTmp                 = StringUtil.emptyToNull(privId);
        String userIdTmp                 = StringUtil.emptyToNull(userId);
        String brokerOrBranchNameTmp     = StringUtil.emptyToNull(brokerOrBranchName);
        String employeeNameTmp           = StringUtil.emptyToNull(employeeName);

        DataList<ModifyEmailAddressForCertifyModel> sqlResult = new DataList<ModifyEmailAddressForCertifyModel>();
        sqlResult.setDataList(
                mapper.getCertifyInfoList(
                        privIdTmp,
                        userIdTmp,
                        brokerOrBranchNameTmp,
                        employeeNameTmp,
                        brokerChargeList,
                        String.valueOf(maxRow))
                );

        if (sqlResult.getDataList().size() > 0) {
            count = sqlResult.getDataList().get(0).getTotalRow();
        }

        sqlResult.setMaxRownum(maxRow);
        sqlResult.setTotalSize(count);
        if (maxRow < count) {
            sqlResult.setOverMaxRownum(true);
        }

        return sqlResult;
    }
    
    /**
     * 更新用
     * @throws Exception
     */
    public int updateMailInfo(ModifyEmailAddressForCertifyModel model) throws Exception {
        logger.debug("MediateChargeInfoDaoImpl.updateMailInfo: model：" + model);

        return updateMailInfoInternal(model);
    }

    private int updateMailInfoInternal(ModifyEmailAddressForCertifyModel model) throws Exception {
        logger.debug("MediateChargeInfoDaoImpl.updateMailInfoInternal: model：" + model);
        int sqlResult = mapper.updateMailInfo(model);
        return sqlResult;
    }
    
    /**
     * チェック用
     * @throws Exception
     */
    public Integer existCnt(String userId) throws Exception {
        logger.debug("CancelMultiplyCertifyDaoImpl.existCnt: userId：" + userId);

        return mapper.existCnt(userId);
    }
    
}
