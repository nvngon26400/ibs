
package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBCustomerInfoModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;
import com.sbisec.helios.ap.service.Service;

public interface IpopoBBApplyUploadService extends Service {
    
    public IpopoBBApplyUploadModel getIpopoBBBrandInfo(String brandCode) throws Exception;
    
    public IpopoUploadBBCustomerOverMaxCheckModel getOverMaxSairyouCount(String butenCode, String accountNumber,
            String brandCode) throws Exception;
    
    public List<BBInvestorAttModel> getBBInvestorAttInfoList(String brandCode, String bbPresentAtionFromYmd)
            throws Exception;
    
    public IpopoBBCustomerInfoModel getIpopoBBCustomerInfo(String butenCode, String accountNumber) throws Exception;
    
    public int getBBAcceptInfoCount(String brandCode, String bbPresentationFromYmd, String butenCode,
            String accountNumber) throws Exception;
    
    public int insertUploadIpopoBBApplyInfo(List<IpopoUploadBBAcceptModel> insertList) throws Exception;
    
    public SectionModel getSectionInfo(String butenCode, String accountNumber) throws Exception;
    
    public IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfo(String butenCode, String accountNumber)
            throws Exception;
    
    public OutputFct001Dto getFct001(String butenCode, String accountNumber) throws Exception;
    
    public OutputFct003Dto getFct003(String butenCode, String accountNumber) throws Exception;
    
    public OutputFct006Dto getFct006(String butenCode, String accountNumber, String brandCode, String invitationType,
            String orderMethod) throws Exception;
    
    public List<MCode> getMCodeList(String codeType, String code1, String code2) throws Exception;
}
