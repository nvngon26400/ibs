package com.sbisec.helios.ap.common.composite.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.composite.dao.IfaBrandSearchDao;
import com.sbisec.helios.ap.common.composite.dao.mapper.IfaBrandSearchMapper;

import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql001ResponseModel;

import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql002ResponseModel;

import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql003ResponseModel;

import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql004RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql004ResponseModel;

import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql005RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql005ResponseModel;





/**
 * 画面ID：CC014
 * 画面名：画面共通部品_銘柄検索
 * @author <author-name>
 *
 * 2023/08/21 新規作成
 */
@Component
public class IfaBrandSearchDaoImpL extends RowSelectableDao implements IfaBrandSearchDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandSearchDaoImpL.class);
    @Autowired
    private IfaBrandSearchMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandSearchSql001RequestModel
     * レスポンスクラス：IfaBrandSearchSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchSql001ResponseModel> selectIfaBrandSearchSql001(IfaBrandSearchSql001RequestModel req)
            throws Exception {
        
        DataList<IfaBrandSearchSql001ResponseModel> res = new DataList<IfaBrandSearchSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandSearchSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandSearchSql002RequestModel
     * レスポンスクラス：IfaBrandSearchSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchSql002ResponseModel> selectIfaBrandSearchSql002(IfaBrandSearchSql002RequestModel req)
            throws Exception {
        
        DataList<IfaBrandSearchSql002ResponseModel> res = new DataList<IfaBrandSearchSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandSearchSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandSearchSql003RequestModel
     * レスポンスクラス：IfaBrandSearchSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchSql003ResponseModel> selectIfaBrandSearchSql003(IfaBrandSearchSql003RequestModel req)
            throws Exception {
        
        DataList<IfaBrandSearchSql003ResponseModel> res = new DataList<IfaBrandSearchSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandSearchSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandSearchSql004RequestModel
     * レスポンスクラス：IfaBrandSearchSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchSql004ResponseModel> selectIfaBrandSearchSql004(IfaBrandSearchSql004RequestModel req)
            throws Exception {
        
        DataList<IfaBrandSearchSql004ResponseModel> res = new DataList<IfaBrandSearchSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandSearchSql004(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandSearchSql005RequestModel
     * レスポンスクラス：IfaBrandSearchSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchSql005ResponseModel> selectIfaBrandSearchSql005(IfaBrandSearchSql005RequestModel req)
            throws Exception {
        
        DataList<IfaBrandSearchSql005ResponseModel> res = new DataList<IfaBrandSearchSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandSearchSql005(req));
        return res;
    }
    
    
    
    
}
