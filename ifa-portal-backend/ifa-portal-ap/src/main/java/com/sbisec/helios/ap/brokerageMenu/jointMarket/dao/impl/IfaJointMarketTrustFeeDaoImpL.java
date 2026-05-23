package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.IfaJointMarketTrustFeeDao;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.mapper.IfaJointMarketTrustFeeMapper;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001ResponseModel;

/**
 * 画面ID：SUB0207_02
 * 画面名：共同店舗　信託報酬
 *
 * @author SBI大連 董
 2024/12/16 新規作成
 */
@Component
public class IfaJointMarketTrustFeeDaoImpL extends RowSelectableDao implements IfaJointMarketTrustFeeDao {
    
    @Autowired
    private IfaJointMarketTrustFeeMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：共同店舗　信託報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
	@Override
    public DataList<IfaJointMarketTrustFeeSql001ResponseModel> selectIfaJointMarketTrustFeeSql001(
    		IfaJointMarketTrustFeeSql001RequestModel req) throws Exception {
        
        DataList<IfaJointMarketTrustFeeSql001ResponseModel> res = new DataList<IfaJointMarketTrustFeeSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJointMarketTrustFeeSql001(req));
        return res;
    }


    
}
