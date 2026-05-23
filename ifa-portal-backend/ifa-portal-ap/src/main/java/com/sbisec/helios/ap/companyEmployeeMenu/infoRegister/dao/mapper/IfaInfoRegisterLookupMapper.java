package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Mapper
public interface IfaInfoRegisterLookupMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql001RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoRegisterLookupSql001ResponseModel> selectIfaInfoRegisterLookupSql001(
            @Param("req") IfaInfoRegisterLookupSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：情報明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql002RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoRegisterLookupSql002ResponseModel> selectIfaInfoRegisterLookupSql002(
            @Param("req") IfaInfoRegisterLookupSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：お知らせテーブル情報削除
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql003(@Param("req") IfaInfoRegisterLookupSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：お知らせ既読テーブル情報削除
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql004(@Param("req") IfaInfoRegisterLookupSql004RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：お知らせ参照権限テーブル情報削除
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql005(@Param("req") IfaInfoRegisterLookupSql005RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：添付ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql006RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaInfoRegisterLookupSql006ResponseModel> selectIfaInfoRegisterLookupSql006(
            @Param("req") IfaInfoRegisterLookupSql006RequestModel req) throws Exception;
}
