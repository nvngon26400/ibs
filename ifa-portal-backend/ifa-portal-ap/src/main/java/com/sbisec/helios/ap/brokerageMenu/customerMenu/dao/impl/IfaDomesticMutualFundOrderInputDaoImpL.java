package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundOrderInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticMutualFundOrderInputMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003ResponseModel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0401-02_1
 * 画面名：国内投信注文入力
 *
 * @author SCSK
 */
@Component
public class IfaDomesticMutualFundOrderInputDaoImpL extends RowSelectableDao
        implements IfaDomesticMutualFundOrderInputDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderInputDaoImpL.class);
    
    @Autowired
    private IfaDomesticMutualFundOrderInputMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：短期売却確認の期間取得
     * SQLタイプ：select
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql001ResponseModel
     *
     * @return レスポンス
     * @exception Exception 短期売却確認の期間取得で例外が発生した場合
     */
    @Override
    public IfaDomesticMutualFundOrderInputSql001ResponseModel selectIfaDomesticMutualFundOrderInputSql001()
            throws Exception {
        
        return mapper.selectIfaDomesticMutualFundOrderInputSql001();
    }
    
    /**
     * SQLID：Sql002
     * SQL名：償還前売却確認の期間取得
     * SQLタイプ：select
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql002ResponseModel
     *
     * @return レスポンス
     * @exception Exception 償還前売却確認の期間取得で例外が発生した場合
     */
    @Override
    public IfaDomesticMutualFundOrderInputSql002ResponseModel selectIfaDomesticMutualFundOrderInputSql002()
            throws Exception {
        
        return mapper.selectIfaDomesticMutualFundOrderInputSql002();
    }
    
    /**
     * SQLID：Sql003
     * SQL名：目論見書チェック用情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundOrderInputSql003RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql003ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel> selectIfaDomesticMutualFundOrderInputSql003(
    		IfaDomesticMutualFundOrderInputSql003RequestModel req) throws Exception {
        
        DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel> res = new DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel>();     
        res.setDataList(mapper.selectIfaDomesticMutualFundOrderInputSql003(req));
        return res;
    }
}
