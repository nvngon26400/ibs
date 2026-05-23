package com.sbisec.helios.ap.broker.service;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.service.Service;
import com.sbisec.helios.ap.broker.model.ImprintInquiryModel;

/**
 * 印影照会（PAPY）のServiceクラス
 */
public interface ImprintInquiryService extends Service {
    
    /**
     * 印影情報を取得する
     *
     * @param ifaIdentifier IFA識別子
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return DataList
     */
    public DataList<ImprintInquiryModel> getImprintImage(String ifaIdentifier, String butenCode, String accountNumber)
            throws Exception;
    
}
