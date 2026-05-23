package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001ResponseModel;





/**
 * 
 * 画面ID：FCT018
 * 画面名：サービス時間チェック（外国）
 * @author AKKODiS 齋藤
 *
 * 2023/08/23 新規作成
 */
@Mapper
public interface Fct018Mapper {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct018Sql001RequestModel
     * レスポンスクラス：Fct018Sql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<Fct018Sql001ResponseModel> selectFct018Sql001(
        @Param("req") Fct018Sql001RequestModel req
        ) throws Exception;
    
    
    
    
}
