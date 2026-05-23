package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql006ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql007ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql008ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql009RequestModel;


/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaSelfInspectItemManageDao {

    /**
     * SQLID：Sql001
     * SQL名：登録年月dropDownLisTデータ取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql001RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @return 登録年月dropDownLisTデータ
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql001ResponseModel> selectIfaSelfInspectItemManageSql001() throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：業者区分名称取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql002RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql002ResponseModel
     *
     * @return 業者区分名称
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql002ResponseModel> selectIfaSelfInspectItemManageSql002() throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：自己点検項目情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql003RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql003ResponseModel
     *
     * @param req パラメータ
     * @return 自己点検項目情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql003ResponseModel> selectIfaSelfInspectItemManageSql003(
            IfaSelfInspectItemManageSql003RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：業者区分存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql006RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql006ResponseModel
     *
     * @param req パラメータ
     * @return 業者区分存在チェック
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql006ResponseModel> selectIfaSelfInspectItemManageSql006(
            IfaSelfInspectItemManageSql006RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：業者区分重複チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql007RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql007ResponseModel
     *
     * @param req パラメータ
     * @return 業者区分重複チェック
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql007ResponseModel> selectIfaSelfInspectItemManageSql007(
            IfaSelfInspectItemManageSql007RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：登録済み自己点検項目チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectItemManageSql008RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql008ResponseModel
     *
     * @param req パラメータ
     * @return 登録済み自己点検項目チェック
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageSql008ResponseModel> selectIfaSelfInspectItemManageSql008(
            IfaSelfInspectItemManageSql008RequestModel req
    ) throws Exception;


    /**
     * SQLID：Sql004
     * SQL名：自己点検項目情報更新
     * SQLタイプ：update
     * リクエストクラス：IfaSelfInspectItemManageSql004RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql004ResponseModel
     *
     * @param req パラメータ
     * @return 挿入行数
     * @exception Exception システムエラー
     */
    public int updateIfaSelfInspectItemManageSql004(
            IfaSelfInspectItemManageSql004RequestModel req
    ) throws Exception;



    /**
     * SQLID：Sql005
     * SQL名：自己点検項目情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaSelfInspectItemManageSql005RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql005ResponseModel
     *
     * @param req パラメータ
     * @return 削除行数
     * @exception Exception システムエラー
     */
    public int deleteIfaSelfInspectItemManageSql005(
            IfaSelfInspectItemManageSql005RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql009
     * SQL名：登録済み自己点検項目データ削除
     * SQLタイプ：delete
     * リクエストクラス：IfaSelfInspectItemManageSql009RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql009ResponseModel
     *
     * @param req パラメータ
     * @return 削除行数
     * @exception Exception システムエラー
     */
    public int deleteIfaSelfInspectItemManageSql009(
            IfaSelfInspectItemManageSql009RequestModel req
    ) throws Exception;

}
