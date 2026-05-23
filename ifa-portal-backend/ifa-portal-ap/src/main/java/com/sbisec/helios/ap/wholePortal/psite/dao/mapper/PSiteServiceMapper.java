
package com.sbisec.helios.ap.wholePortal.psite.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.wholePortal.psite.model.GetDealerNameAndRegistNoModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTbMedDisclaimerModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PSiteServiceMapper {
    
    public List<GetTempFileDirForDocumentCategoryIdModel> getTempFileDirForDocumentCategoryId(
            @Param("funcId") String funcId, @Param("catId") String catId) throws Exception;
    
    public List<GetDealerNameAndRegistNoModel> getDealerNameAndRegistNo(@Param("brokerCode") String brokerCode)
            throws Exception;
    
    public List<GetTbMedDisclaimerModel> getTbMedDisclaimer(@Param("disclaimerId") String disclaimerId)
            throws Exception;
    
}
