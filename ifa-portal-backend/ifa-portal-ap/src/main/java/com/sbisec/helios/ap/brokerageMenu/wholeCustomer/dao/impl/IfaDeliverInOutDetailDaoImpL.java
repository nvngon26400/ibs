package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDeliverInOutDetailDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaDeliverInOutDetailMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001ResponseModel;





/**
 * 画面ID：SUB020302_0204-01
 * 画面名：入出庫明細
 * @author <author-name>
 *
 * 2024/04/03 新規作成
 */
@Component
public class IfaDeliverInOutDetailDaoImpL extends RowSelectableDao implements IfaDeliverInOutDetailDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDeliverInOutDetailDaoImpL.class);
    @Autowired
    private IfaDeliverInOutDetailMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：入出庫明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaDeliverInOutDetailSql001RequestModel
     * レスポンスクラス：IfaDeliverInOutDetailSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailSql001ResponseModel> selectIfaDeliverInOutDetailSql001(IfaDeliverInOutDetailSql001RequestModel req)
            throws Exception {
        
        DataList<IfaDeliverInOutDetailSql001ResponseModel> res = new DataList<IfaDeliverInOutDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDeliverInOutDetailSql001(req));
        return res;
    }
    
    
    
    
}
