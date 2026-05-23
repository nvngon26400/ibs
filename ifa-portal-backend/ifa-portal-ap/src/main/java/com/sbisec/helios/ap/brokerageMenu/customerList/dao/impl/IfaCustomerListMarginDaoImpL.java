package com.sbisec.helios.ap.brokerageMenu.customerList.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListMarginDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper.IfaCustomerListMarginMapper;

import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001ResponseModel;

/**
 * 画面ID：SUB0201_02-01
 * 画面名：顧客一覧・信用
 * @author <author-name>
 *
 * 2024/01/11 新規作成
 */
@Component
public class IfaCustomerListMarginDaoImpL extends RowSelectableDao implements IfaCustomerListMarginDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerListMarginDaoImpL.class);
    @Autowired
    private IfaCustomerListMarginMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListMarginSql001RequestModel
     * レスポンスクラス：IfaCustomerListMarginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerListMarginSql001ResponseModel> selectIfaCustomerListMarginSql001(IfaCustomerListMarginSql001RequestModel req)
            throws Exception {
        
        DataList<IfaCustomerListMarginSql001ResponseModel> res = new DataList<IfaCustomerListMarginSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerListMarginSql001(req));
        return res;
    }
    
    
    
    
}
