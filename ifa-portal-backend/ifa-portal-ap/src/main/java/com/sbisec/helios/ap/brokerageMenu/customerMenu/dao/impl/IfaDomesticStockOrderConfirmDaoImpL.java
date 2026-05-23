package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticStockOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticStockOrderConfirmMapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql002RequestModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0208-01_3
 * 画面名：国内株式注文確認
 * @author <author-name>
 *
 * 2023/11/08 新規作成
 */
@Component
public class IfaDomesticStockOrderConfirmDaoImpL extends RowSelectableDao implements IfaDomesticStockOrderConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderConfirmDaoImpL.class);
    
    @Autowired
    private IfaDomesticStockOrderConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticStockOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaDomesticStockOrderConfirmSql001(IfaDomesticStockOrderConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaDomesticStockOrderConfirmSql001(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderConfirmSql002(IfaDomesticStockOrderConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaDomesticStockOrderConfirmSql002(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderConfirmSql002b(IfaDomesticStockOrderConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaDomesticStockOrderConfirmSql002b(req);
    }

}
