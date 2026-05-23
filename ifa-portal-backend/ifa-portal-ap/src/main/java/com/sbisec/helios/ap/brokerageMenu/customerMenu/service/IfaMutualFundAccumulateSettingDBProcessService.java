package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.service.Service;

/**
 * 投信積立設定
 * 
 * @author nicksen.li
 *
 * 2025/08/27 新規作成
 */
public interface IfaMutualFundAccumulateSettingDBProcessService extends Service {

    /**
     * 投信積立設定解除確認(DB 登録)
     * 
     * @param IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel insSql001Req
     * @return DB登録結果
     * @throws Exception
     */
    public int cancelConfirmSql001(IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel insSql001Req)
            throws Exception;

    /**
     * 設定一括変更登録(DB 登録)
     * 
     * @param IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel sql001Req
     * @return DB登録結果
     * @throws Exception
     */
    public int bulkChangeConfirmSql001(IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel sql001Req)
            throws Exception;

}
