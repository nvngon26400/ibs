
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;

public interface IpopoBBApplyUploadDao {
    public IpopoBBApplyUploadModel getIpopoBBBrandInfo(String brandCode);

    public IpopoUploadBBCustomerOverMaxCheckModel getMaybeSairyouCount(String butenCode, String accountNumber);

    public int insertUploadIpopoBBApplyInfoToBBAccept(List<IpopoUploadBBAcceptModel> insertList);

    public int insertUploadIpopoBBApplyInfoToSrAccept(List<IpopoUploadBBAcceptModel> insertList);

    public int getUnSelectionCount(String butenCode, String accountNumber, String brandCode);

    public SectionModel getSectionInfo(String butenCode, String accountNumber);
    
    public IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfo(String butenCode, String accountNumber);
}