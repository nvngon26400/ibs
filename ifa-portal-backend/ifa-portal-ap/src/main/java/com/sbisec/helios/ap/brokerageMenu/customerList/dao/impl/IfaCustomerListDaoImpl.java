package com.sbisec.helios.ap.brokerageMenu.customerList.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper.IfaCustomerListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql004ResponseModel;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本
 *
 * @author SCSK池田
 * 
   2023/09/13 新規作成
 *
 */
@Component
public class IfaCustomerListDaoImpl extends RowSelectableDao implements IfaCustomerListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerListDaoImpl.class);
    
    @Autowired
    private IfaCustomerListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql001RequestModel
     * レスポンスクラス：IfaCustomerListSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return SQL001検索結果
     * @exception Exception 例外
    
     */
    public DataList<IfaCustomerListSql001ResponseModel> selectIfaCustomerListSql001(
            IfaCustomerListSql001RequestModel req) throws Exception {
        
        DataList<IfaCustomerListSql001ResponseModel> res = new DataList<IfaCustomerListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerListSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql003RequestModel
     * レスポンスクラス：IfaCustomerListSql003ResponseModel
     *
     * @param req リクエストパラメータ
     * @return SQL003検索結果
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql003ResponseModel> selectIfaCustomerListSql003(
            IfaCustomerListSql003RequestModel req) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListSql003ResponseModel.selectIfaCustomerListSql003");
        }
        
        DataList<IfaCustomerListSql003ResponseModel> res = new DataList<IfaCustomerListSql003ResponseModel>();
        res.setDataList(mapper.selectIfaCustomerListSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql004RequestModel
     * レスポンスクラス：IfaCustomerListSql004ResponseModel
     *
     * @return SQL004検索結果
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql004ResponseModel> selectIfaCustomerListSql004() throws Exception {
        
        DataList<IfaCustomerListSql004ResponseModel> res = new DataList<IfaCustomerListSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerListSql004());
        return res;
    }
    
}
