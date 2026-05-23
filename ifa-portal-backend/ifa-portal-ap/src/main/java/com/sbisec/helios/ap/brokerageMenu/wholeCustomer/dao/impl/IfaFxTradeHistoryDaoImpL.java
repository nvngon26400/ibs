package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaFxTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaFxTradeHistoryMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003ResponseModel;

/**
 * 画面ID：SUB020302_0202-01
 * 画面名：為替取引履歴
 *
 * @author SCSK川崎
 2024/05/08 新規作成
 */
@Component
public class IfaFxTradeHistoryDaoImpL extends RowSelectableDao implements IfaFxTradeHistoryDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxTradeHistoryDaoImpL.class);
    
    @Autowired
    private IfaFxTradeHistoryMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：為替取引履歴一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql001RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistorySql001ResponseModel> selectIfaFxTradeHistorySql001(
            IfaFxTradeHistorySql001RequestModel req) throws Exception {
        
        DataList<IfaFxTradeHistorySql001ResponseModel> res = new DataList<IfaFxTradeHistorySql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaFxTradeHistorySql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：通貨リストコード取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql002RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistorySql002ResponseModel> selectIfaFxTradeHistorySql002(
            IfaFxTradeHistorySql002RequestModel req) throws Exception {
        
        DataList<IfaFxTradeHistorySql002ResponseModel> res = new DataList<IfaFxTradeHistorySql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaFxTradeHistorySql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：為替取引履歴コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql003RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistorySql003ResponseModel> selectIfaFxTradeHistorySql003(
            IfaFxTradeHistorySql003RequestModel req) throws Exception {
        
        DataList<IfaFxTradeHistorySql003ResponseModel> res = new DataList<IfaFxTradeHistorySql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaFxTradeHistorySql003(req));
        return res;
    }
}
