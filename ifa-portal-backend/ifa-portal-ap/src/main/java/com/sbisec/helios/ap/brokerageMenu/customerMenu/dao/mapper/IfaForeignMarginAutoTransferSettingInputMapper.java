package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB0202_0306-01_1
 * 画面名：米株信用自動振替設定入力
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 */
@Mapper
public interface IfaForeignMarginAutoTransferSettingInputMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：当日店頭取引売却注文のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingInputSql001RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingInputSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginAutoTransferSettingInputSql001ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql001(
            @Param("req") IfaForeignMarginAutoTransferSettingInputSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002_1
     * SQL名：当日店頭取引買付注文のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingInputSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingInputSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql002_1(
            @Param("req") IfaForeignMarginAutoTransferSettingInputSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002_2
     * SQL名：外債買付代金の入力のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingInputSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingInputSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql002_2(
            @Param("req") IfaForeignMarginAutoTransferSettingInputSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：遡る時間の取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingInputSql003RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingInputSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginAutoTransferSettingInputSql003ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql003(
            @Param("req") IfaForeignMarginAutoTransferSettingInputSql003RequestModel req) throws Exception;
}
