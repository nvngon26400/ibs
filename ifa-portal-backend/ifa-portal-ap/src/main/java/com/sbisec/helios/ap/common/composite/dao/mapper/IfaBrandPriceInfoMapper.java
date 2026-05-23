package com.sbisec.helios.ap.common.composite.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003ResponseModel;

/**
 * 
 * 画面ID：CC013
 * 画面名：銘柄時価情報（国内株）
 * @author <author-name>
 *
 * 2023/08/24 新規作成
 */
@Mapper
public interface IfaBrandPriceInfoMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql001RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaBrandPriceInfoSql001ResponseModel> selectIfaBrandPriceInfoSql001(
            @Param("req") IfaBrandPriceInfoSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql002RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaBrandPriceInfoSql002ResponseModel> selectIfaBrandPriceInfoSql002(
            @Param("req") IfaBrandPriceInfoSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql003RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql003ResponseModel
     * @param req リクエストパラメータ
     * @return 呼値管理番号
     * @exception Exception システムエラー
     */
    public List<IfaBrandPriceInfoSql003ResponseModel> selectIfaBrandPriceInfoSql003(
            @Param("req") IfaBrandPriceInfoSql003RequestModel req) throws Exception;
    
}
