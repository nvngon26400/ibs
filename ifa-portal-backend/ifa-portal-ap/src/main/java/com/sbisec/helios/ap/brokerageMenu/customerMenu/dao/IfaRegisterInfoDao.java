package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql001Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql002Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql003Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql004Model;


/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連苗
 */
public interface IfaRegisterInfoDao {
    
    /**
     * SQLID：Sql001
     * SQL名：優先表示分類情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql001Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql001Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql001Model> selectIfaRegisterInfoSql001(IfaRegisterInfoRequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：優先表示分類ヘッダ取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql002Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql002Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql002Model> selectIfaRegisterInfoSql002(IfaRegisterInfoRequestModel req)
            throws Exception;
        
    /**
     * SQLID：Sql003
     * SQL名：分類情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql003Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql003Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql003Model> selectIfaRegisterInfoSql003(IfaRegisterInfoRequestModel req)
            throws Exception;

    
    /**
     * SQLID：Sql004
     * SQL名：登録情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql004Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql004Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql004Model> selectIfaRegisterInfoSql004(IfaRegisterInfoRequestModel req)
            throws Exception;
        
    
}
