package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaFxTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaFxTradeOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003aResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003bResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author 鄒
 * 
 * 2023/11/20 新規作成
 *
 */
@Component
public class IfaFxTradeOrderCancelConfirmDaoImpl extends RowSelectableDao implements IfaFxTradeOrderCancelConfirmDao{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxTradeOrderCancelConfirmDaoImpl.class);
    
    @Autowired
    private IfaFxTradeOrderCancelConfirmMapper mapper;
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql001RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxTradeOrderCancelConfirmSql001(IfaFxTradeOrderCancelConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaFxTradeOrderCancelConfirmSql001(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql002RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaFxTradeOrderCancelConfirmSql002(IfaFxTradeOrderCancelConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaFxTradeOrderCancelConfirmSql002(req);
    }

    /**
     * SQLID：Sql003a
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql003RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003aResponseModel selectIfaFxTradeOrderCancelConfirmSql003a(IfaFxTradeOrderCancelConfirmSql003RequestModel req)
            throws Exception {
        
        return mapper.selectIfaFxTradeOrderCancelConfirmSql003a(req);
    }
    
    /**
     * SQLID：Sql003b
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql003RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003bResponseModel selectIfaFxTradeOrderCancelConfirmSql003b(IfaFxTradeOrderCancelConfirmSql003RequestModel req)
            throws Exception {
        
        return mapper.selectIfaFxTradeOrderCancelConfirmSql003b(req);
    }
    
    /**
     * SQLID：Sql004
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql004RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxTradeOrderCancelConfirmSql004()
            throws Exception {
        
        return mapper.selectIfaFxTradeOrderCancelConfirmSql004();
    }
    
}
