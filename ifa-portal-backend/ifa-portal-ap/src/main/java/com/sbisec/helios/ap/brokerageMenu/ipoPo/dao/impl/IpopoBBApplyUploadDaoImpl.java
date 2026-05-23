package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyUploadDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.BBAcceptMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IpopoBBApplyUploadMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.SRAcceptMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;

@Component
public class IpopoBBApplyUploadDaoImpl extends RowSelectableDao implements IpopoBBApplyUploadDao {
    private static final Logger logger = LoggerFactory.getLogger(IpopoBBApplyUploadDaoImpl.class);

    @Autowired
    private IpopoBBApplyUploadMapper mapper;
    @Autowired
    private BBAcceptMapper bbAcceptMapper;
    @Autowired
    private SRAcceptMapper srAcceptMapper;

    @Override
    public IpopoBBApplyUploadModel getIpopoBBBrandInfo(String brandCode) {
        logger.debug("IpopoBBApplyUploadDaoImpl.getIpopoBBBrandInfo: brandCode：" + brandCode);
        return getIpopoBBBrandInfoInternal(brandCode);
    }

    private IpopoBBApplyUploadModel getIpopoBBBrandInfoInternal(String brandCode) {
        logger.debug("IpopoBBApplyUploadDaoImpl.getIpopoBBBrandInfoInternal: brandCode：" + brandCode);
        return mapper.getIpopoBBBrandInfo(brandCode);
    }

    @Override
    public IpopoUploadBBCustomerOverMaxCheckModel getMaybeSairyouCount(String butenCode, String accountNumber) {
        logger.debug("IpopoBBApplyUploadServiceImplL.toCheckOverMaxSairyouCount: butenCode：" + butenCode
                + ",accountNumber:" + accountNumber);
        return getMaybeSairyouCountInternal(butenCode, accountNumber);
    }

    private IpopoUploadBBCustomerOverMaxCheckModel getMaybeSairyouCountInternal(String butenCode,
            String accountNumber) {
        logger.debug("IpopoBBApplyUploadServiceImplL.getMaybeSairyouCountInternal: butenCode：" + butenCode
                + ",accountNumber:" + accountNumber);
        return mapper.getMaybeSairyouCount(butenCode, accountNumber);
    }

    @Override
    public int insertUploadIpopoBBApplyInfoToBBAccept(List<IpopoUploadBBAcceptModel> insertList) {
        logger.debug("IpopoBBApplyUploadServiceImplL.insertUploadIpopoBBApplyInfoToBBAccept: insertList：" + insertList);
        return insertUploadIpopoBBApplyInfoToBBAcceptInternal(insertList);
    }

    private int insertUploadIpopoBBApplyInfoToBBAcceptInternal(List<IpopoUploadBBAcceptModel> insertList) {
        logger.debug("IpopoBBApplyUploadServiceImplL.insertUploadIpopoBBApplyInfoToBBAcceptInternal: insertList："
                + insertList);
        // 件数が多すぎるとエラーが発生する可能性があるので、リストは割合する
        // 50件毎に登録する
        int maxCount = 50;
        // 登録次数を計算する
        List<IpopoUploadBBAcceptModel> divisionList = new ArrayList<IpopoUploadBBAcceptModel>();

        int cnt = 1;
        for (int i = 0; i < insertList.size(); i++) {
            if (cnt % maxCount == 0) {
                bbAcceptMapper.insertUploadIpopoBBApplyInfoToBBAccept(divisionList);
                divisionList.clear();
                cnt = 1;
            }
            divisionList.add(insertList.get(i));
            cnt++;
        }
        if (divisionList != null && divisionList.size() >= 0) {
            bbAcceptMapper.insertUploadIpopoBBApplyInfoToBBAccept(divisionList);
        }
        return 1;
    }

    @Override
    public int insertUploadIpopoBBApplyInfoToSrAccept(List<IpopoUploadBBAcceptModel> insertList) {
        logger.debug("IpopoBBApplyUploadServiceImplL.insertUploadIpopoBBApplyInfoToSrAccept: insertList：" + insertList);
        return insertUploadIpopoBBApplyInfoToSrAcceptInternal(insertList);
    }

    private int insertUploadIpopoBBApplyInfoToSrAcceptInternal(List<IpopoUploadBBAcceptModel> insertList) {
        logger.debug("IpopoBBApplyUploadServiceImplL.insertUploadIpopoBBApplyInfoToSrAcceptInternal: insertList："
                + insertList);
        // 件数が多すぎるとエラーが発生する可能性があるので、リストは割合する
        // 50件毎に登録する
        int maxCount = 50;
        // 登録次数を計算する
        List<IpopoUploadBBAcceptModel> divisionList = new ArrayList<IpopoUploadBBAcceptModel>();

        int cnt = 1;
        for (int i = 0; i < insertList.size(); i++) {
            if (cnt % maxCount == 0) {
                srAcceptMapper.insertUploadIpopoBBApplyInfoToSrAccept(divisionList);
                divisionList.clear();
                cnt = 1;
            }
            divisionList.add(insertList.get(i));
            cnt++;
        }
        if (divisionList != null && divisionList.size() >= 0) {
            srAcceptMapper.insertUploadIpopoBBApplyInfoToSrAccept(divisionList);
        }
        return 1;
    }

    @Override
    public int getUnSelectionCount(String butenCode, String accountNumber, String brandCode) {
        logger.debug("IpopoBBApplyUploadServiceImplL.getUnSelectionCount: butenCode：" + butenCode + ",accountNumber:"
                + accountNumber + ",brandCode:" + brandCode);
        return mapper.getUnSelectionCount(butenCode, accountNumber, brandCode);
    }

    @Override
    public SectionModel getSectionInfo(String butenCode, String accountNumber) {
        logger.debug("IpopoBBApplyUploadServiceImplL.getSectionInfo: butenCode：" + butenCode + ",accountNumber:"
                + accountNumber);
        return getSectionInfoInternal(butenCode, accountNumber);
    }

    private SectionModel getSectionInfoInternal(String butenCode, String accountNumber) {
        logger.debug("IpopoBBApplyUploadServiceImplL.getSectionInfoInternal: butenCode：" + butenCode + ",accountNumber:"
                + accountNumber);
        return mapper.getSectionInfo(butenCode, accountNumber);
    }

    @Override
    public IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfo(String butenCode, String accountNumber) {
        logger.debug("IpopoBBApplyUploadServiceImplL.getEdelivAgreementInfo: butenCode：" + butenCode + ",accountNumber:"
                + accountNumber);
        return getEdelivAgreementInfoInternal(butenCode, accountNumber);
    }
    
    private IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfoInternal(String butenCode, String accountNumber) {
        return mapper.getEdelivAgreementInfo(butenCode, accountNumber);
    }

}
