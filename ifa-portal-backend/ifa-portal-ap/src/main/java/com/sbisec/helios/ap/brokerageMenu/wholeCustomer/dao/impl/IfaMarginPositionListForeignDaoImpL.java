package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMarginPositionListForeignDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaMarginPositionListForeignMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001ResponseModel;





/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 * @author <author-name>
 *
 * 2023/11/30 新規作成
 */
@Component
public class IfaMarginPositionListForeignDaoImpL extends RowSelectableDao implements IfaMarginPositionListForeignDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPositionListForeignDaoImpL.class);
    @Autowired
    private IfaMarginPositionListForeignMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客口座情報検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListForeignSql001RequestModel
     * レスポンスクラス：IfaMarginPositionListForeignSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListForeignSql001ResponseModel> selectIfaMarginPositionListForeignSql001(IfaMarginPositionListForeignSql001RequestModel req)
            throws Exception {
        
        DataList<IfaMarginPositionListForeignSql001ResponseModel> res = new DataList<IfaMarginPositionListForeignSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaMarginPositionListForeignSql001(req));
        return res;
    }
    
    
    
    
}
