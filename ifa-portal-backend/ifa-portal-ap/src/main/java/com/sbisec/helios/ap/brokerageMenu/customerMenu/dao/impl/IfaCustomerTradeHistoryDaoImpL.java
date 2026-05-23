package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaCustomerTradeHistoryMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001ResponseModel;


/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Component
public class IfaCustomerTradeHistoryDaoImpL extends RowSelectableDao implements IfaCustomerTradeHistoryDao {
    
    @Autowired
    private IfaCustomerTradeHistoryMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：取引履歴（顧客別） 一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerTradeHistorySql001RequestModel
     * レスポンスクラス：IfaCustomerTradeHistorySql001ResponseModel
     *
     * @param req パラメータ
     * @return 取引履歴（顧客別） 一覧
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaCustomerTradeHistorySql001ResponseModel> selectIfaCustomerTradeHistorySql001(
            IfaCustomerTradeHistorySql001RequestModel req
    ) throws Exception {
        
        DataList<IfaCustomerTradeHistorySql001ResponseModel> res = new DataList<IfaCustomerTradeHistorySql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerTradeHistorySql001(req));
        return res;
    }
}
