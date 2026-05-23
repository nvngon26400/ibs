package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaFxOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaFxOrderConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author <author-name>
 *
 * 2023/09/25 新規作成
 */
@Component
public class IfaFxOrderConfirmDaoImpL extends RowSelectableDao implements IfaFxOrderConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxOrderConfirmDaoImpL.class);
    
    @Autowired
    private IfaFxOrderConfirmMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaFxOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaFxOrderConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaFxOrderConfirmSql002(IfaFxOrderConfirmSql002RequestModel req) throws Exception {
        
        return mapper.updateIfaFxOrderConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaFxOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaFxOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxOrderConfirmSql001(IfaFxOrderConfirmSql001RequestModel req) throws Exception {
        
        return mapper.insertIfaFxOrderConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxOrderConfirmSql003() throws Exception {
        
        return mapper.selectIfaFxOrderConfirmSql003();
    }
    
}
