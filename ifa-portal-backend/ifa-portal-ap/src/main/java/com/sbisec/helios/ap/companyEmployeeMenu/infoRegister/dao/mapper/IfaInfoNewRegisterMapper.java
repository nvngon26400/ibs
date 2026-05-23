package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper;

import java.util.List;

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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@Mapper
public interface IfaInfoNewRegisterMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoNewRegisterSql001ResponseModel> selectIfaInfoNewRegisterSql001() throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：お知らせテーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql002(@Param("req") IfaInfoNewRegisterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：新お知らせ参照権限テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql003(@Param("req") IfaInfoNewRegisterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：新お知らせ既読テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoNewRegisterSql004(@Param("req") IfaInfoNewRegisterSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：全担当者メールアドレス取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoNewRegisterSql005ResponseModel> selectIfaInfoNewRegisterSql005(
            @Param("req") IfaInfoNewRegisterSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：送信者メールアドレス取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoNewRegisterSql007ResponseModel> selectIfaInfoNewRegisterSql007(
            @Param("req") IfaInfoNewRegisterSql007RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：登録ファイルディレクトリ取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoNewRegisterSql008ResponseModel> selectIfaInfoNewRegisterSql008(
            @Param("req") IfaInfoNewRegisterSql008RequestModel req) throws Exception;
    
}
