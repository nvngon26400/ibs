package com.sbisec.helios.ap.brokerageMenu.customerList.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListFuturesOptionsDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper.IfaCustomerListFuturesOptionsMapper;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
 2024/05/29 新規作成
 */
@Component
public class IfaCustomerListFuturesOptionsDaoImpl extends RowSelectableDao implements IfaCustomerListFuturesOptionsDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerListFuturesOptionsDaoImpl.class);
    
    @Autowired
    private IfaCustomerListFuturesOptionsMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListFuturesOptionsSql001RequestModel
     * レスポンスクラス：IfaCustomerListFuturesOptionsSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerListFuturesOptionsSql001ResponseModel> selectIfaCustomerListFuturesOptionsSql001(
            IfaCustomerListFuturesOptionsSql001RequestModel req) throws Exception {
        
        DataList<IfaCustomerListFuturesOptionsSql001ResponseModel> res = new DataList<IfaCustomerListFuturesOptionsSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerListFuturesOptionsSql001(req));
        return res;
    }
    
}
