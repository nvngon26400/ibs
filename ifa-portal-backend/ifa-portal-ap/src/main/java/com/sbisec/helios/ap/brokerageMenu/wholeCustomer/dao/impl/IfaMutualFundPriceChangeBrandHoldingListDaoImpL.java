package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMutualFundPriceChangeBrandHoldingListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaMutualFundPriceChangeBrandHoldingListMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020301_03-01
 * 画面名：投信基準価額変動の銘柄保有一覧
 * @author <author-name>
 *
 * 2024/04/12 新規作成
 */
@Component
public class IfaMutualFundPriceChangeBrandHoldingListDaoImpL extends RowSelectableDao
        implements IfaMutualFundPriceChangeBrandHoldingListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundPriceChangeBrandHoldingListDaoImpL.class);
    
    @Autowired
    private IfaMutualFundPriceChangeBrandHoldingListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：投信基準価額変動情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel
     * レスポンスクラス：IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> selectIfaMutualFundPriceChangeBrandHoldingListSql001(
            IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel req) throws Exception {
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> res = new DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundPriceChangeBrandHoldingListSql001(req));
        return res;
    }
    
}
