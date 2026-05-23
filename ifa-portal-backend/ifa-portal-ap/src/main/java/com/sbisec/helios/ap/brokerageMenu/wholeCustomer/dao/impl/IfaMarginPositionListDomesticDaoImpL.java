package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMarginPositionListDomesticDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaMarginPositionListDomesticMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaMarginPositionListDomesticMapperMariaDB;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002ResponseModel;

/**
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
@Component
public class IfaMarginPositionListDomesticDaoImpL extends RowSelectableDao implements IfaMarginPositionListDomesticDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPositionListDomesticDaoImpL.class);
    
    @Autowired
    private IfaMarginPositionListDomesticMapper mapper;
    
    @Autowired
    private IfaMarginPositionListDomesticMapperMariaDB mapperMaria;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListDomesticSql001RequestModel
     * レスポンスクラス：IfaMarginPositionListDomesticSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListDomesticSql001ResponseModel> selectIfaMarginPositionListDomesticSql001(
            IfaMarginPositionListDomesticSql001RequestModel req) throws Exception {
        
        DataList<IfaMarginPositionListDomesticSql001ResponseModel> res = new DataList<IfaMarginPositionListDomesticSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaMarginPositionListDomesticSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListDomesticSql002RequestModel
     * レスポンスクラス：IfaMarginPositionListDomesticSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListDomesticSql002ResponseModel> selectIfaMarginPositionListDomesticSql002(
            IfaMarginPositionListDomesticSql002RequestModel req) throws Exception {
        
        DataList<IfaMarginPositionListDomesticSql002ResponseModel> res = new DataList<IfaMarginPositionListDomesticSql002ResponseModel>();
        
        res.setDataList(mapperMaria.selectIfaMarginPositionListDomesticSql002(req));
        return res;
    }
}
