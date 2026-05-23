package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql005ResponseModel;

/**
 * 画面ID：SUB0501_02-01
 * 画面名：資料種別一覧
 *
 * @author SCSK
 *     2024/02/05 新規作成
 *
 */
public interface IfaDocClassListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：カテゴリ一覧
     * SQLタイプ：select
     * リクエストクラス：IfaDocClassListSql001RequestModel
     * レスポンスクラス：IfaDocClassListSql001ResponseModel
     *
     * @param req リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    public DataList<IfaDocClassListSql001ResponseModel> selectIfaDocClassListSql001(
            IfaDocClassListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：カテゴリ登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDocClassListSql002RequestModel
     *
     * @param req リクエストパラメタ
     * @return 登録レコード数
     * @exceptionException カテゴリ登録処理で例外が発生した場合
     */
    public int insertIfaDocClassListSql002(IfaDocClassListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：カテゴリ更新
     * SQLタイプ：update
     * リクエストクラス：IfaDocClassListSql003RequestModel
     * レスポンスクラス：IfaDocClassListSql003ResponseModel
     *
     * @param req リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新処理で例外が発生した場合
     */
    public int updateIfaDocClassListSql003(
            IfaDocClassListSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：カテゴリ削除
     * SQLタイプ：update
     * リクエストクラス：IfaDocClassListSql004RequestModel
     *
     * @param req リクエストパラメタ
     * @return 更新レコード数
     * @exceptionException カテゴリ削除処理で例外が発生した場合
     */
    public int updateIfaDocClassListSql004(IfaDocClassListSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：登録存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaDocClassListSql005RequestModel
     * レスポンスクラス：IfaDocClassListSql005ResponseModel
     *
     * @param req リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException 登録存在チェック処理で例外が発生した場合
     */
    public DataList<IfaDocClassListSql005ResponseModel> selectIfaDocClassListSql005(
            IfaDocClassListSql005RequestModel req) throws Exception;
    
}
