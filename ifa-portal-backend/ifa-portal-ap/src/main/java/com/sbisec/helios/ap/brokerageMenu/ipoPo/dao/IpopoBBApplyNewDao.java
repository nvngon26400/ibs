
package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBCustomerInfoModel;

public interface IpopoBBApplyNewDao {
    
    /**
     * 投資家属性のプルダウンリスト情報を取得する.
     * 
     * @param bbProductCode      銘柄コード
     * @param bbPresentationFrom ブックビルディング申込期間（開始）(YYYYMMDD)
     * @return 投資家属性のプルダウンリスト情報
     * @throws Exception 異常
     */
    public List<BBInvestorAttModel> getBBInvestorAttInfoList(String bbProductCode, String bbPresentationFrom)
            throws Exception;
    
    /**
     * 顧客情報を取得する.
     * 
     * @param butenCode      部店コード
     * @param accountNumber  口座番号
     * @throws Exception 異常
     */
    public IpopoBBCustomerInfoModel getIpopoBBCustomerInfo(String butenCode, String accountNumber) throws Exception;
    
    /**
     * 入力データがブックビルディング受付に存在する件数を取得する.
     * 
     * @param bbProductCode      銘柄コード
     * @param bbPresentationFrom ブックビルディング申込期間（開始）(YYYYMMDD)
     * @param butenCode          部店コード
     * @param accountNumber      口座番号
     * @return データ存在件数
     * @throws Exception 異常
     */
    public int getBBAcceptInfoCount(String bbProductCode, String bbPresentationFrom, String butenCode,
            String accountNumber) throws Exception;
    
}
