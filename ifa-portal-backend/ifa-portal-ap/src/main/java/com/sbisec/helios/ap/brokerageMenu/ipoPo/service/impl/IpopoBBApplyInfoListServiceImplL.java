package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyInfoListDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IpopoBBApplyInfoListService;

@Component(value = "cmpIpopoBBApplyInfoListService")
public class IpopoBBApplyInfoListServiceImplL implements IpopoBBApplyInfoListService {

    private static final Logger logger = LoggerFactory.getLogger(IpopoBBApplyInfoListServiceImplL.class);
    
    @Autowired
    private IpopoBBApplyInfoListDao ipopoBBApplyInfoListDao;

    /**
     * STARアップロードファイル出力一覧画面検索処理.
     * @param brokerChargeList 仲介業者・営業員リスト
     * 
     * @return BB申込一覧
     * @throws Exception 異常
     */
    @Override
    public DataList<IpopoBBApplyInfoListModel> getIpopoBBApplyInfoList() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("IpopoBBApplyInfoListServiceImplL.getIpopoBrandInfoList");
        }
        return ipopoBBApplyInfoListDao.getIpopoBBApplyInfoList();
    }

    /**
     * star upload file download
     * 
     * @param sessionId
     * @param userId
     * @param sysId
     * @param path
     * @param sysDate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvDownload(String sessionId, String userId,
            String sysId, String path, String sysDate, String minKey, String maxKey) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("IpopoBBApplyInfoListServiceImplL.ipopoStarUploadCsvDownload");
        }
        return ipopoBBApplyInfoListDao.ipopoStarUploadCsvDownload(sessionId, userId, sysId, path, sysDate, minKey, maxKey);

    }

    /**
     * 注文状態と時間より、レコードを取得する。
     * @param orderStatus
     * @param sysDate
     * @return
     * @throws Exception
     */
    @Override
    public Integer getOrderStatusCount(String orderStatus, String sysDate, String minKey, String maxKey) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("IpopoBBApplyInfoListServiceImplL.getOrderStatusCount");
        }
        return ipopoBBApplyInfoListDao.getOrderStatusCount(orderStatus, sysDate, minKey, maxKey);
    }
}