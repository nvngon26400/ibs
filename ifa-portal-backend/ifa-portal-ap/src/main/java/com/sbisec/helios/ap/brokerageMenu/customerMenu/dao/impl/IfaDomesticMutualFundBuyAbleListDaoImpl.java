package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundBuyAbleListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticMutualFundBuyAbleListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入積立可能一覧
 *
 * @author WJL
 *
 *     2025/03/30 新規作成
 */
@Component
public class IfaDomesticMutualFundBuyAbleListDaoImpl extends RowSelectableDao implements IfaDomesticMutualFundBuyAbleListDao {
    
    @Autowired
    private IfaDomesticMutualFundBuyAbleListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaDomesticMutualFundBuyAbleListSql001ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql001(
            IfaDomesticMutualFundBuyAbleListSql001RequestModel req) throws Exception {
        
        DataList<IfaDomesticMutualFundBuyAbleListSql001ResponseModel> res = new DataList<IfaDomesticMutualFundBuyAbleListSql001ResponseModel>();     
        res.setDataList(mapper.selectIfaDomesticMutualFundBuyAbleListSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql002RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql002(
            IfaDomesticMutualFundBuyAbleListSql002RequestModel req) throws Exception {
        
        DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> res = new DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel>();     
        res.setDataList(mapper.selectIfaDomesticMutualFundBuyAbleListSql002(req));
        return res;
    }
 
}
