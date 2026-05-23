
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;

public interface IpopoBrandInfoListDao {
    
    /**
     * 一覧画面検索処理.
     *
     * @return IPO/PO銘柄一覧
     * @throws Exception 異常
     */
    public DataList<IpopoBrandInfoListModel> getIpopoBrandInfoList() throws Exception;
    
    /**
     * 銘柄存在処理.
     *
     * @return 銘柄情報
     * @throws Exception 異常
     */
    public BBProductMasterModel getBBProductMasterInfo(String bbProductCode, String bbPresentationFrom)
            throws Exception;
}
