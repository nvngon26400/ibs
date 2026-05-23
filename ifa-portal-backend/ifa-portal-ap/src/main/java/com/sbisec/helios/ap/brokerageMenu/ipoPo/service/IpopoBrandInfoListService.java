
package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;
import com.sbisec.helios.ap.service.Service;

public interface IpopoBrandInfoListService extends Service {
    
    /**
     * 一覧画面検索処理.
     *
     * @return IPO/PO銘柄一覧
     * @throws Exception 異常
     */
    public DataList<IpopoBrandInfoListModel> getIpopoBrandInfoList(String dummy) throws Exception;
    
    /**
     * 銘柄存在処理.
     *
     * @return 銘柄情報
     * @throws Exception 異常
     */
    public BBProductMasterModel getBBProductMasterInfo(String bbProductCode, String bbPresentationFrom)
            throws Exception;
}
