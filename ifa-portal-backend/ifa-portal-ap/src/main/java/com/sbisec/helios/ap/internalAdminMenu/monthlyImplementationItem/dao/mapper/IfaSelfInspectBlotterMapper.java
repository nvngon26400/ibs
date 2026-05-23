package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Mapper
public interface IfaSelfInspectBlotterMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：表示年月dropDownLisTデータ取得
     * SQLタイプ：
     * レスポンスクラス：IfaSelfInspectBlotterSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterSql001ResponseModel> selectIfaSelfInspectBlotterSql001() throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：最終更新日時取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql002RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterSql002ResponseModel> selectIfaSelfInspectBlotterSql002(
            @Param("req") IfaSelfInspectBlotterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：自己点検確認日取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql003RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterSql003ResponseModel> selectIfaSelfInspectBlotterSql003(
            @Param("req") IfaSelfInspectBlotterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：未登録自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql004RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterSql004ResponseModel> selectIfaSelfInspectBlotterSql004(
            @Param("req") IfaSelfInspectBlotterSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：登録自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql005RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterSql005ResponseModel> selectIfaSelfInspectBlotterSql005(
            @Param("req") IfaSelfInspectBlotterSql005RequestModel req) throws Exception;
    
}
