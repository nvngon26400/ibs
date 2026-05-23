
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IpopoBBApplyUploadMapper {

    public IpopoBBApplyUploadModel getIpopoBBBrandInfo(@Param("brandCode") String brandCode);
    
    public IpopoUploadBBCustomerOverMaxCheckModel getMaybeSairyouCount(@Param("butenCode") String butenCode,
            @Param("accountNumber") String accountNumber);

    public int getUnSelectionCount(@Param("butenCode") String butenCode, @Param("accountNumber") String accountNumber,
            @Param("brandCode") String brandCode);
    
    public SectionModel getSectionInfo(@Param("butenCode") String butenCode,
            @Param("accountNumber") String accountNumber);

    public IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfo(@Param("butenCode") String butenCode,
            @Param("accountNumber") String accountNumber);

}