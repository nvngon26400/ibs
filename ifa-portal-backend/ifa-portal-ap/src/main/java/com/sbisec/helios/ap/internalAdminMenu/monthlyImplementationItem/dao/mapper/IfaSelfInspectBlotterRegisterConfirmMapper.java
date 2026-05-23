package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.annotation.dao.CordysMapper;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel;

import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@CordysMapper
public interface IfaSelfInspectBlotterRegisterConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：シーケンス取得
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql001()
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：自己点検テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaSelfInspectBlotterRegisterConfirmSql002(
            @Param("req") IfaSelfInspectBlotterRegisterConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：自己点検確認テーブル登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaSelfInspectBlotterRegisterConfirmSql003(
            @Param("req") IfaSelfInspectBlotterRegisterConfirmSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：自己点検確認テーブル更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaSelfInspectBlotterRegisterConfirmSql004(
            @Param("req") IfaSelfInspectBlotterRegisterConfirmSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業システム値情報取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql005(
            @Param("req") IfaSelfInspectBlotterRegisterConfirmSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：自己点検記録簿今月未読件数取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql006(
            @Param("req") IfaSelfInspectBlotterRegisterConfirmSql006RequestModel req) throws Exception;
    
}
