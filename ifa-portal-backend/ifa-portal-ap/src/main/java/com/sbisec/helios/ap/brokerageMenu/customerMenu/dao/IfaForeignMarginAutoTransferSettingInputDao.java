package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003ResponseModel;

/**
 * 画面ID：SUB0202_0306-01_1
 * 画面名：米株信用自動振替設定入力
 * @author <author-name>
 * 
 * 2023/11/10 新規作成
 *
 */
public interface IfaForeignMarginAutoTransferSettingInputDao {
    
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
    public DataList<IfaForeignMarginAutoTransferSettingInputSql001ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql001(
            IfaForeignMarginAutoTransferSettingInputSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002_1
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingInputSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingInputSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql002_1(
            IfaForeignMarginAutoTransferSettingInputSql002RequestModel req) throws Exception;
    
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
    public DataList<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql002_2(
            IfaForeignMarginAutoTransferSettingInputSql002RequestModel req) throws Exception;
    
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
    public DataList<IfaForeignMarginAutoTransferSettingInputSql003ResponseModel> selectIfaForeignMarginAutoTransferSettingInputSql003(
            IfaForeignMarginAutoTransferSettingInputSql003RequestModel req) throws Exception;
    
}
