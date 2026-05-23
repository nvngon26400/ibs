package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006ResponseModel;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 *
 */
public interface IfaInfoRegisterViewerSettingDao {
    
    /**
     * SQLID：Sql002
     * SQL名：全担当者設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql002RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql002ResponseModel> selectIfaInfoRegisterViewerSettingSql002(
            IfaInfoRegisterViewerSettingSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：権限設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql003RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql003ResponseModel> selectIfaInfoRegisterViewerSettingSql003(
            IfaInfoRegisterViewerSettingSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：個別担当者設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql004RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql004ResponseModel> selectIfaInfoRegisterViewerSettingSql004(
            IfaInfoRegisterViewerSettingSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業者リスト検索
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql005RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql005ResponseModel> selectIfaInfoRegisterViewerSettingSql005(
            IfaInfoRegisterViewerSettingSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：担当者リスト検索
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql006RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql006ResponseModel> selectIfaInfoRegisterViewerSettingSql006(
            IfaInfoRegisterViewerSettingSql006RequestModel req) throws Exception;
    
}
