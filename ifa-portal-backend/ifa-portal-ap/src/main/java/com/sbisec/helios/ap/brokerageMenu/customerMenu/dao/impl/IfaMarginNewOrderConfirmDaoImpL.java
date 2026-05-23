package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginNewOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMarginNewOrderConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql002RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author <author-name>
 *
 * 2023/10/13 新規作成
 */
@Component
public class IfaMarginNewOrderConfirmDaoImpL extends RowSelectableDao implements IfaMarginNewOrderConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderConfirmDaoImpL.class);
    
    @Autowired
    private IfaMarginNewOrderConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginNewOrderConfirmSql001RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMarginNewOrderConfirmSql001(IfaMarginNewOrderConfirmSql001RequestModel req) throws Exception {
        
        return mapper.insertIfaMarginNewOrderConfirmSql001(req);
        
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderConfirmSql002_1RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaMarginNewOrderConfirmSql002(IfaMarginNewOrderConfirmSql002RequestModel req) throws Exception {
        
        return mapper.updateIfaMarginNewOrderConfirmSql002(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaMarginNewOrderConfirmSql002b(IfaMarginNewOrderConfirmSql002RequestModel req) throws Exception {
        
        return mapper.updateIfaMarginNewOrderConfirmSql002b(req);
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
    public String selectIfaMarginNewOrderConfirmSql003() throws Exception {
        
        return mapper.selectIfaMarginNewOrderConfirmSql003();
    }
    
}
