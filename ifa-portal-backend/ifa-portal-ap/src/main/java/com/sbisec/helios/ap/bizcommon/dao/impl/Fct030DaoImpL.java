package com.sbisec.helios.ap.bizcommon.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.dao.Fct030Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct030Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004ResponseModel;

/**
 * 画面ID：FCT030
 * 画面名：仲介業者コード営業員リスト取得
 * @author AKKODiS　齋藤
 *
 * 2023/08/24 新規作成
 */
@Component
public class Fct030DaoImpL extends RowSelectableDao implements Fct030Dao {
    
    @Autowired
    private Fct030Mapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct030Sql001RequestModel
     * レスポンスクラス：Fct030Sql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Fct030Sql001ResponseModel> selectFct030Sql001(Fct030Sql001RequestModel req) throws Exception {
        
        DataList<Fct030Sql001ResponseModel> res = new DataList<Fct030Sql001ResponseModel>();
        
        res.setDataList(mapper.selectFct030Sql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct030Sql002RequestModel
     * レスポンスクラス：Fct030Sql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Fct030Sql002ResponseModel> selectFct030Sql002(Fct030Sql002RequestModel req) throws Exception {
        
        DataList<Fct030Sql002ResponseModel> res = new DataList<Fct030Sql002ResponseModel>();
        
        res.setDataList(mapper.selectFct030Sql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct030Sql003RequestModel
     * レスポンスクラス：Fct030Sql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Fct030Sql003ResponseModel> selectFct030Sql003(Fct030Sql003RequestModel req) throws Exception {
        
        DataList<Fct030Sql003ResponseModel> res = new DataList<Fct030Sql003ResponseModel>();
        
        res.setDataList(mapper.selectFct030Sql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct030Sql004RequestModel
     * レスポンスクラス：Fct030Sql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Fct030Sql004ResponseModel> selectFct030Sql004(Fct030Sql004RequestModel req) throws Exception {
        
        DataList<Fct030Sql004ResponseModel> res = new DataList<Fct030Sql004ResponseModel>();
        
        res.setDataList(mapper.selectFct030Sql004(req));
        return res;
    }
    
}
