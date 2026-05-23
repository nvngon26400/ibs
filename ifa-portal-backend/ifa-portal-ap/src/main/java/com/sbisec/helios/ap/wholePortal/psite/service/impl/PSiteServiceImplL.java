
package com.sbisec.helios.ap.wholePortal.psite.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.psite.dao.PSiteServiceDao;
import com.sbisec.helios.ap.wholePortal.psite.model.GetDealerNameAndRegistNoModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTbMedDisclaimerModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;
import com.sbisec.helios.ap.wholePortal.psite.service.PSiteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSiteServiceImplL implements PSiteService {
    
    private static final Logger logger = LoggerFactory.getLogger(PSiteServiceImplL.class);
    
    @Autowired
    private PSiteServiceDao dao;
    
    public DataList<GetTempFileDirForDocumentCategoryIdModel> getTempFileDirForDocumentCategoryId(String funcId,
            String catId) throws Exception {
        
        if (logger.isDebugEnabled())
            logger.debug("PSiteServiceImplL.getTempFileDirForDocumentCategoryId");
        
        return dao.getTempFileDirForDocumentCategoryId(funcId, catId);
    }
    
    public DataList<GetDealerNameAndRegistNoModel> getDealerNameAndRegistNo(String brokerCode) throws Exception {
        
        if (logger.isDebugEnabled())
            logger.debug("PSiteServiceImplL.getDealerNameAndRegistNo");
        
        return dao.getDealerNameAndRegistNo(brokerCode);
    }
    
    public DataList<GetTbMedDisclaimerModel> getTbMedDisclaimer(String disclaimerId) throws Exception {
        
        if (logger.isDebugEnabled())
            logger.debug("PSiteServiceImplL.getTbMedDisclaimer");
        
        return dao.getTbMedDisclaimer(disclaimerId);
    }
    
}
