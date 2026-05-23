package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDomesticMutualFundOrderOtherInfoDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaDomesticMutualFundOrderOtherInfoMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel;





/**
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author BASE 李
 2024/06/20 新規作成
 */
@Component
public class IfaDomesticMutualFundOrderOtherInfoDaoImpL extends RowSelectableDao implements IfaDomesticMutualFundOrderOtherInfoDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderOtherInfoDaoImpL.class);
    @Autowired
    private IfaDomesticMutualFundOrderOtherInfoMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：国内投信注文その他情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundOrderOtherInfoSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel> selectIfaDomesticMutualFundOrderOtherInfoSql001(IfaDomesticMutualFundOrderOtherInfoSql001RequestModel req)
            throws Exception {
        
        DataList<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel> res = new DataList<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDomesticMutualFundOrderOtherInfoSql001(req));
        return res;
    }
    
    
    
    
}
