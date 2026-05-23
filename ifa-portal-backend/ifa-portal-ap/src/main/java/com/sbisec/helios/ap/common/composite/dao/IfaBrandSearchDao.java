package com.sbisec.helios.ap.common.composite.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

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
 *
 */
public interface IfaBrandSearchDao {
    
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
            throws Exception;
    
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
            throws Exception;
    
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
            throws Exception;
    
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
            throws Exception;
    
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
            throws Exception;
    
    
    
    
}
