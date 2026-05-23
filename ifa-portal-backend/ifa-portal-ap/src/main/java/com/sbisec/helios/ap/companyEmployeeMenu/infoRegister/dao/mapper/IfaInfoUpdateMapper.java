package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008ResponseModel;


import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql003RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql005RequestModel;


/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */
@Mapper
public interface IfaInfoUpdateMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql001RequestModel
     * レスポンスクラス：IfaInfoUpdateSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoUpdateSql001ResponseModel> selectIfaInfoUpdateSql001(
            @Param("req") IfaInfoUpdateSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：情報明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql002RequestModel
     * レスポンスクラス：IfaInfoUpdateSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoUpdateSql002ResponseModel> selectIfaInfoUpdateSql002(
            @Param("req") IfaInfoUpdateSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：登録ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql008RequestModel
     * レスポンスクラス：IfaInfoUpdateSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoUpdateSql008ResponseModel> selectIfaInfoUpdateSql008(
            @Param("req") IfaInfoUpdateSql008RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql004
     * SQL名：新お知らせ参照権限テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoUpdateSql004(
            @Param("req")  IfaInfoUpdateSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：新お知らせ既読テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoUpdateSql005(
            @Param("req")  IfaInfoUpdateSql005RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：新お知らせテーブル更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaInfoUpdateSql003(
            @Param("req")  IfaInfoUpdateSql003RequestModel req
        ) throws Exception;
    
    
}
