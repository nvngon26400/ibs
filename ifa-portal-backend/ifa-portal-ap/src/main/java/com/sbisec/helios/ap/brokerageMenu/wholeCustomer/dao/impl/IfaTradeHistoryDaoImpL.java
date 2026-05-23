package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaTradeHistoryMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 画面ID：SUB020302_0201-01
 * 画面名：取引履歴
 *
 * @author SCSK
 2024/06/13 新規作成
 */
@Component
public class IfaTradeHistoryDaoImpL extends RowSelectableDao implements IfaTradeHistoryDao {
    
    @Autowired
    private IfaTradeHistoryMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：取引履歴情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql001RequestModel
     * レスポンスクラス：IfaTradeHistorySql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistorySql001ResponseModel> selectIfaTradeHistorySql001(IfaTradeHistorySql001RequestModel req)
            throws Exception {
        
        DataList<IfaTradeHistorySql001ResponseModel> res = new DataList<IfaTradeHistorySql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaTradeHistorySql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：取引履歴画面コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql002RequestModel
     * レスポンスクラス：IfaTradeHistorySql002ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistorySql002ResponseModel> selectIfaTradeHistorySql002()
            throws Exception {
        
        DataList<IfaTradeHistorySql002ResponseModel> res = new DataList<IfaTradeHistorySql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaTradeHistorySql002());
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：現株ポイント参照可能仲介業者チェック
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql003RequestModel
     * レスポンスクラス：IfaTradeHistorySql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistorySql003ResponseModel> selectIfaTradeHistorySql003(
            IfaTradeHistorySql003RequestModel req) throws Exception {
        
        DataList<IfaTradeHistorySql003ResponseModel> res = new DataList<IfaTradeHistorySql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaTradeHistorySql003(req));
        return res;
    }
    
}
