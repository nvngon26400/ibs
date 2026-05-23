
package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBrandInfoListDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IpopoBrandInfoListService;

@Component(value = "IpopoBrandInfoListService")
public class IpopoBrandInfoListServiceImplL implements IpopoBrandInfoListService {
    
    private static final Logger logger = LoggerFactory.getLogger(IpopoBrandInfoListServiceImplL.class);
    
    @Autowired
    private IpopoBrandInfoListDao ipopoBrandInfoListDao;
    
    public void setIpopoBrandInfoListDao(IpopoBrandInfoListDao ipopoBrandInfoListDao) {
        
        this.ipopoBrandInfoListDao = ipopoBrandInfoListDao;
        logger.debug("DI: setIpopoBrandInfoListDao:[" + ipopoBrandInfoListDao + "] of IpopoBrandInfoListServiceImplL:["
                + this + "]");
    }
    
    /**
     * 一覧画面検索処理
     *
     * @return IPO/PO銘柄一覧
     * @throws Exception 異常
     */
    @Override
    public DataList<IpopoBrandInfoListModel> getIpopoBrandInfoList(String dummy) throws Exception {
        
        if (logger.isDebugEnabled()) {
            logger.debug("IpopoBrandInfoListServiceImplL.getIpopoBrandInfoList");
        }
        return ipopoBrandInfoListDao.getIpopoBrandInfoList();
    }
    
    /**
     * 銘柄存在処理
     *
     * @return 銘柄情報
     * @throws Exception 異常
     */
    @Override
    public BBProductMasterModel getBBProductMasterInfo(String bbProductCode, String bbPresentationFrom)
            throws Exception {
        
        if (logger.isDebugEnabled()) {
            logger.debug("IpopoBrandInfoListServiceImplL.getBBProductMasterInfo");
        }
        return ipopoBrandInfoListDao.getBBProductMasterInfo(bbProductCode, bbPresentationFrom);
    }
}
