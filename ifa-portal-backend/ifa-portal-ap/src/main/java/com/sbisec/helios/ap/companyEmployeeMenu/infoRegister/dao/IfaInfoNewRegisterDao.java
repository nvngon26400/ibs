package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql007ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql008ResponseModel;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 *
 */
public interface IfaInfoNewRegisterDao {
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     * リクエストクラス：selectIfaInfoNewRegisterSql001RequestModel
     * レスポンスクラス：selectIfaInfoNewRegisterSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterSql001ResponseModel> selectIfaInfoNewRegisterSql001() throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：全担当者メールアドレス取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoNewRegisterSql005RequestModel
     * レスポンスクラス：IfaInfoNewRegisterSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterSql005ResponseModel> selectIfaInfoNewRegisterSql005(
            IfaInfoNewRegisterSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：送信者メールアドレス取得
     * SQLタイプ：select
     * リクエストクラス：selectIfaInfoNewRegisterSql007RequestModel
     * レスポンスクラス：selectIfaInfoNewRegisterSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterSql007ResponseModel> selectIfaInfoNewRegisterSql007(
            IfaInfoNewRegisterSql007RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：登録ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：selectIfaInfoNewRegisterSql008RequestModel
     * レスポンスクラス：selectIfaInfoNewRegisterSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterSql008ResponseModel> selectIfaInfoNewRegisterSql008(
            IfaInfoNewRegisterSql008RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：お知らせテーブル登録
     * SQLタイプ：insert
     * リクエストクラス：insertIfaInfoNewRegisterSql002RequestModel
     * レスポンスクラス：insertIfaInfoNewRegisterSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql002(IfaInfoNewRegisterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：お知らせ参照権限テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：insertIfaInfoNewRegisterSql003RequestModel
     * レスポンスクラス：insertIfaInfoNewRegisterSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql003(IfaInfoNewRegisterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：お知らせ既読テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：insertIfaInfoNewRegisterSql004RequestModel
     * レスポンスクラス：insertIfaInfoNewRegisterSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql004(IfaInfoNewRegisterSql004RequestModel req) throws Exception;
}
