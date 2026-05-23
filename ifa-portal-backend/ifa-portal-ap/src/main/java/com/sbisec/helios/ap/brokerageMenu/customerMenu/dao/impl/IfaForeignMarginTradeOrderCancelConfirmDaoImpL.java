package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignMarginTradeOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel;

/**
 * 画面ID：SUB0202_0303-03_1
 * 画面名：米株信用取引注文取消確認
 * @author 齋藤優輝
 *
 * 2023/09/16 新規作成
 */
@Component
public class IfaForeignMarginTradeOrderCancelConfirmDaoImpL extends RowSelectableDao
        implements IfaForeignMarginTradeOrderCancelConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginTradeOrderCancelConfirmDaoImpL.class);
    
    @Autowired
    private IfaForeignMarginTradeOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaForeignMarginTradeOrderCancelConfirmSql001(
            IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel req) throws Exception {
        
        return mapper.insertIfaForeignMarginTradeOrderCancelConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaForeignMarginTradeOrderCancelConfirmSql002(
            IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel req) throws Exception {
        
        return mapper.updateIfaForeignMarginTradeOrderCancelConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> selectIfaForeignMarginTradeOrderCancelConfirmSql003(
            IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> res = new DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginTradeOrderCancelConfirmSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel> selectIfaForeignMarginTradeOrderCancelConfirmSql004(
            IfaForeignMarginTradeOrderCancelConfirmSql004RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel> res = new DataList<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginTradeOrderCancelConfirmSql004());
        return res;
    }
    
}
