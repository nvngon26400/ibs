package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;

public interface IpopoBBApplyInfoListDao {

    /**
     * STARアップロードファイル出力一覧画面検索処理.
     * 
     * @return BB申込一覧
     * @throws Exception 異常
     */
    public DataList<IpopoBBApplyInfoListModel> getIpopoBBApplyInfoList() throws Exception;

    /**
     * star upload file download
     * 
     * @param sessionId
     * @param userId
     * @param sysId
     * @param path
     * @param sysDate
     * @param maxKey 
     * @param minKey 
     * @return
     * @throws Exception
     */
    public DataList<IpopoStarUploadCsvInfoListModel> ipopoStarUploadCsvDownload(String sessionId, String userId,
            String sysId, String path, String sysDate, String minKey, String maxKey) throws Exception;

    /**
     * 注文状態と時間より、レコードを取得する。
     * @param orderStatus
     * @param sysDate
     * @param maxKey 
     * @param minKey 
     * @return
     * @throws Exception
     */
    public Integer getOrderStatusCount(String orderStatus, String sysDate, String minKey, String maxKey) throws Exception;
}