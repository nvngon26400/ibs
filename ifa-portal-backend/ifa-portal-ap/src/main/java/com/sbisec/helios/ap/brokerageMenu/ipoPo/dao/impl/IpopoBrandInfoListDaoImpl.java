package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBrandInfoListDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.BBProductMasterMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IpopoBrandInfoListMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;

@Component
public class IpopoBrandInfoListDaoImpl extends RowSelectableDao implements IpopoBrandInfoListDao {
    
    private static final Logger logger = LoggerFactory.getLogger(IpopoBrandInfoListDaoImpl.class);
    
    @Autowired
    private IpopoBrandInfoListMapper ipopoBrandInfoListMapper;
    
    @Autowired
    protected BBProductMasterMapper bBProductMasterMapper;
    
    /**
     * 一覧画面検索処理.
     *
     * @return IPO/PO銘柄一覧
     * @throws Exception 異常
     */
    @Override
    public DataList<IpopoBrandInfoListModel> getIpopoBrandInfoList() throws Exception {
        
        logger.debug("IpopoBrandInfoListDaoImpl.getIpopoBrandInfoList");
        return getIpopoBrandInfoListInternal();
    }
    
    private DataList<IpopoBrandInfoListModel> getIpopoBrandInfoListInternal() throws Exception {
        
        logger.debug("IpopoBrandInfoListDaoImpl.getIpopoBrandInfoListInternal");
        
        String querySizeLimit = String.valueOf(maxRownum);
        int queryLimitedTo = maxRownum;
        int count = 0;
        
        DataList<IpopoBrandInfoListModel> dataList = new DataList<IpopoBrandInfoListModel>();
        List<IpopoBrandInfoListModel> ipopoBrandInfoList = ipopoBrandInfoListMapper
                .getIpopoBrandInfoList(querySizeLimit);
        dataList.setDataList(ipopoBrandInfoList);
        
        if (dataList.getDataList().size() > 0) {
            count = dataList.getDataList().get(0).getTotalRow();
        }
        dataList.setMaxRownum(queryLimitedTo);
        dataList.setTotalSize(count);
        if (maxRownum < count) {
            dataList.setOverMaxRownum(true);
        }
        return dataList;
    }
    
    /**
     * 銘柄存在処理.
     *
     * @return 銘柄情報
     * @throws Exception 異常
     */
    @Override
    public BBProductMasterModel getBBProductMasterInfo(String bbProductCode, String bbPresentationFrom)
            throws Exception {
        
        logger.debug("IpopoBrandInfoListDaoImpl.getBBProductMasterInfo: bbProductCode：" + bbProductCode
                + ",bbPresentationFrom：" + bbPresentationFrom);
        return getBBProductMasterInfoInternal(bbProductCode, bbPresentationFrom);
    }
    
    private BBProductMasterModel getBBProductMasterInfoInternal(String bbProductCode, String bbPresentationFrom)
            throws Exception {
        
        logger.debug("IpopoBrandInfoListDaoImpl.getBBProductMasterInfoInternal: bbProductCode：" + bbProductCode
                + ",bbPresentationFrom：" + bbPresentationFrom);
        
        String bbProductCodeParam = StringUtil.emptyToNull(bbProductCode);
        String bbPresentationFromParam = StringUtil.emptyToNull(bbPresentationFrom);
        
        logger.debug("IpopoBrandInfoListDaoImpl.getBBProductMasterInfoInternal");
        
        return bBProductMasterMapper.getBBProductMasterInfo(bbProductCodeParam, bbPresentationFromParam);
    }
}
