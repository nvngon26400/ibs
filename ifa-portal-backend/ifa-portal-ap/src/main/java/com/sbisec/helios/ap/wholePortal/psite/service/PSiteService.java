
package com.sbisec.helios.ap.wholePortal.psite.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.psite.model.GetDealerNameAndRegistNoModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTbMedDisclaimerModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;

import org.springframework.stereotype.Service;

@Service
public interface PSiteService extends com.sbibits.earth.service.Service {
    
    public DataList<GetTempFileDirForDocumentCategoryIdModel> getTempFileDirForDocumentCategoryId(String funcId,
            String catId) throws Exception;
    
    public DataList<GetDealerNameAndRegistNoModel> getDealerNameAndRegistNo(String brokerCode) throws Exception;
    
    public DataList<GetTbMedDisclaimerModel> getTbMedDisclaimer(String disclaimerId) throws Exception;
    
}
