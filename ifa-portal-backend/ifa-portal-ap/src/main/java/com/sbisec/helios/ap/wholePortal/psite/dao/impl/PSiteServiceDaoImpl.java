
package com.sbisec.helios.ap.wholePortal.psite.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.wholePortal.psite.dao.PSiteServiceDao;
import com.sbisec.helios.ap.wholePortal.psite.dao.mapper.PSiteServiceMapper;
import com.sbisec.helios.ap.wholePortal.psite.model.GetDealerNameAndRegistNoModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTbMedDisclaimerModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PSiteServiceDaoImpl extends RowSelectableDao implements PSiteServiceDao {
    
    private static final Logger logger = LoggerFactory.getLogger(PSiteServiceDaoImpl.class);
    
    @Autowired
    private PSiteServiceMapper mapper;
    
    public DataList<GetTempFileDirForDocumentCategoryIdModel> getTempFileDirForDocumentCategoryId(String funcId,
            String catId) throws Exception {
        
        logger.debug("PSiteServiceDaoImpl.getTempFileDirForDocumentCategoryId: funcId,catId");
        
        return getTempFileDirForDocumentCategoryIdInternal(funcId, catId);
    }
    
    private DataList<GetTempFileDirForDocumentCategoryIdModel> getTempFileDirForDocumentCategoryIdInternal(
            String funcId, String catId) throws Exception {
        
        funcId = StringUtil.emptyToNull(funcId);
        catId = StringUtil.emptyToNull(catId);
        
        DataList<GetTempFileDirForDocumentCategoryIdModel> dataList = new DataList<GetTempFileDirForDocumentCategoryIdModel>();
        dataList.setDataList(mapper.getTempFileDirForDocumentCategoryId(funcId, catId));
        return dataList;
        
    }
    
    public DataList<GetDealerNameAndRegistNoModel> getDealerNameAndRegistNo(String brokerCode) throws Exception {
        
        logger.debug("PSiteServiceDaoImpl.getDealerNameAndRegistNo: brokerCode");
        brokerCode = StringUtil.emptyToNull(brokerCode);
        DataList<GetDealerNameAndRegistNoModel> dataList = new DataList<GetDealerNameAndRegistNoModel>();
        
        dataList.setDataList(mapper.getDealerNameAndRegistNo(brokerCode));
        int count = dataList.getDataList().size();
        dataList.setMaxRownum(count);
        dataList.setTotalSize(count);
        dataList.setOverMaxRownum(false);
        
        return dataList;
    }
    
    public DataList<GetTbMedDisclaimerModel> getTbMedDisclaimer(String disclaimerId) throws Exception {
        
        logger.debug("PSiteServiceDaoImpl.getTbMedDisclaimer: brokerCode");
        disclaimerId = StringUtil.emptyToNull(disclaimerId);
        DataList<GetTbMedDisclaimerModel> dataList = new DataList<GetTbMedDisclaimerModel>();
        
        dataList.setDataList(mapper.getTbMedDisclaimer(disclaimerId));
        int count = dataList.getDataList().size();
        dataList.setMaxRownum(count);
        dataList.setTotalSize(count);
        dataList.setOverMaxRownum(false);
        
        return dataList;
    }
    
}
