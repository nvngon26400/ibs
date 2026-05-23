package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004ResponseModel;





/**
 * 
 * 画面ID：FCT030
 * 画面名：仲介業者コード営業員リスト取得
 * @author AKKODiS　齋藤
 *
 * 2023/08/24 新規作成
 */
@Mapper
public interface Fct030Mapper {
    
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
    public List<Fct030Sql001ResponseModel> selectFct030Sql001(
        @Param("req") Fct030Sql001RequestModel req
        ) throws Exception;
    
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
    public List<Fct030Sql002ResponseModel> selectFct030Sql002(
        @Param("req") Fct030Sql002RequestModel req
        ) throws Exception;
    
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
    public List<Fct030Sql003ResponseModel> selectFct030Sql003(
        @Param("req") Fct030Sql003RequestModel req
        ) throws Exception;
    
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
    public List<Fct030Sql004ResponseModel> selectFct030Sql004(
        @Param("req") Fct030Sql004RequestModel req
        ) throws Exception;
    
    
    
    
}
