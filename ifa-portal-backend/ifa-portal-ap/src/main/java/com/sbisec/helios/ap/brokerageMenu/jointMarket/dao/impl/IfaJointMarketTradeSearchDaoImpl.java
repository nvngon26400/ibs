package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.IfaJointMarketTradeSearchDao;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.mapper.IfaJointMarketTradeSearchMapper;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001ResponseModel;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Component
public class IfaJointMarketTradeSearchDaoImpl extends RowSelectableDao implements IfaJointMarketTradeSearchDao {

    @Autowired
    private IfaJointMarketTradeSearchMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：共同店舗取引検索情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointMarketTradeSearchSql001RequestModel
     * レスポンスクラス：IfaJointMarketTradeSearchSql001ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointMarketTradeSearchSql001ResponseModel> selectIfaJointMarketTradeSearchSql001(
        IfaJointMarketTradeSearchSql001RequestModel req) throws Exception {
        DataList<IfaJointMarketTradeSearchSql001ResponseModel> res =
            new DataList<IfaJointMarketTradeSearchSql001ResponseModel>();
        res.setDataList(mapper.selectIfaJointMarketTradeSearchSql001(req));
        return res;
    }

}
