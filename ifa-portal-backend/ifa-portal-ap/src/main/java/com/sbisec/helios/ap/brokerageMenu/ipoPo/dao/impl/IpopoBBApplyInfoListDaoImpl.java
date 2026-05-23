package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyInfoListDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IpopoBBApplyInfoListMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IpopoStarUploadCsvInfoListCsvOut;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

@Component
public class IpopoBBApplyInfoListDaoImpl extends RowSelectableDao implements IpopoBBApplyInfoListDao {

    private static final Logger logger = LoggerFactory.getLogger(IpopoBBApplyInfoListDaoImpl.class);

    @Autowired
    private IpopoBBApplyInfoListMapper ipopoBBApplyInfoListMapper;
    
    @Autowired
    private ComplianceService complianceService;
    
    // CSVUPLOADの最大件数
    private static final String MAX_SIZE = "2000";

    /**
     * STARアップロードファイル出力一覧画面検索処理.
     * 
     * @return BB申込一覧
     * @throws Exception 異常
     */
    public DataList<IpopoBBApplyInfoListModel> getIpopoBBApplyInfoList() throws Exception {
        logger.debug("IpopoBBApplyInfoListDaoImpl.getIpopoBBApplyInfoList");

        return getIpopoBBApplyInfoListInternal();
    }

    private DataList<IpopoBBApplyInfoListModel> getIpopoBBApplyInfoListInternal() throws Exception {
        logger.debug("IpopoBBApplyInfoListDaoImpl.getIpopoBBApplyInfoListInternal");

        int count = 0;

        // 2000件のみ
        String querySizeLimit = MAX_SIZE;
        int queryLimitedTo = Integer.valueOf(MAX_SIZE);

        // 戻り結果
        DataList<IpopoBBApplyInfoListModel> dataList = new DataList<IpopoBBApplyInfoListModel>();
        // STARアップロードファイル出力一覧画面検索処理
        List<IpopoBBApplyInfoListModel> ipopoBBApplyInfoList = ipopoBBApplyInfoListMapper.getIpopoBBApplyInfoList(querySizeLimit);
        // 戻り結果を設定
        dataList.setDataList(ipopoBBApplyInfoList);

        if (dataList.getDataList().size() > 0) {
            count = dataList.getDataList().get(0).getTotalRow();
        }
        dataList.setMaxRownum(queryLimitedTo);
        dataList.setTotalSize(count);
        if (queryLimitedTo < count) {
            dataList.setOverMaxRownum(true);
        }

        return dataList;
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
    public DataList<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvDownload(String sessionId, String userId,
            String sysId, String path, String sysDate, String minKey, String maxKey) throws Exception {
        return ipopoStarUploadCsvDownloadInternal(sessionId, userId, sysId, path, sysDate, minKey, maxKey);
    }

    private DataList<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvDownloadInternal(String sessionId,
            String userId, String sysId, String path, String sysDate, String minKey, String maxKey) throws Exception {
        logger.debug("IpopoBBApplyInfoListDaoImpl.ipopoStarUploadCsvDownloadInternal: sessionId：" + sessionId
                + ",userId：" + userId + ",sysId：" + sysId + ",path：" + path + ",minKey：" + minKey + ",maxKey：" + maxKey);
        // update： 「募集入力済」を「出力対象」に更新 1⇒9
        // 募集入力済
        String statusBefore = "1";
        // 出力対象
        String statusAfter = "9";
        ipopoBBApplyInfoListMapper.updateIpopoOrderStatus(statusBefore, statusAfter, userId, sysId, sysDate, minKey, maxKey);
        // 戻り結果
        DataList<IpopoStarUploadCsvInfoListModel> dataList = new DataList<IpopoStarUploadCsvInfoListModel>();
        List<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvInfoList = ipopoBBApplyInfoListMapper
                .ipopoStarUploadCsvDownload();
        dataList.setTotalSize(ipopoStarUploadCsvInfoList.size());
        dataList.setDataList(ipopoStarUploadCsvInfoList);
        if(ipopoStarUploadCsvInfoList.size() > 0) {
            // Make CSV file
            IpopoStarUploadCsvInfoListCsvOut csvOut = new IpopoStarUploadCsvInfoListCsvOut(complianceService);
            String tmpCsv = csvOut.doCreateCsvFile(dataList, sessionId, userId, null, path);
            dataList.getDataList().clear();
            dataList.setTitle(tmpCsv);
            // update： 「出力対象」を「受付済」に更新
            // 出力対象
            statusBefore = "9";
            // 受付済
            statusAfter = "2";
            ipopoBBApplyInfoListMapper.updateIpopoOrderStatus(statusBefore, statusAfter, userId, sysId, null, minKey, maxKey);
        }
        return dataList;
    }

    /**
     * 注文状態と時間より、レコードを取得する。
     * @param orderStatus
     * @param sysDate
     * @return
     * @throws Exception
     */
    @Override
    public Integer getOrderStatusCount(String orderStatus, String sysDate, String minKey, String maxKey)
            throws Exception {
        logger.debug("IpopoBBApplyInfoListDaoImpl.getOrderStatusCount: orderStatus：" + orderStatus + ",sysDate："
                + sysDate + ",minKey：" + minKey + ",maxKey：" + maxKey);
        return ipopoBBApplyInfoListMapper.getOrderStatusCount(orderStatus, sysDate, minKey, maxKey);
    }
}
