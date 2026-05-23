package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectItemManageDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.mapper.IfaSelfInspectItemManageMapper;
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
@Component
public class IfaSelfInspectItemManageDaoImpL extends RowSelectableDao implements IfaSelfInspectItemManageDao {

    @Autowired
    private IfaSelfInspectItemManageMapper mapper;

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
    @Override
    public DataList<IfaSelfInspectItemManageSql001ResponseModel> selectIfaSelfInspectItemManageSql001() throws Exception {

        DataList<IfaSelfInspectItemManageSql001ResponseModel> res = new DataList<IfaSelfInspectItemManageSql001ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql001());
        return res;
    }

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
    @Override
    public DataList<IfaSelfInspectItemManageSql002ResponseModel> selectIfaSelfInspectItemManageSql002() throws Exception {

        DataList<IfaSelfInspectItemManageSql002ResponseModel> res = new DataList<IfaSelfInspectItemManageSql002ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql002());
        return res;
    }

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
    @Override
    public DataList<IfaSelfInspectItemManageSql003ResponseModel> selectIfaSelfInspectItemManageSql003(
            IfaSelfInspectItemManageSql003RequestModel req
    ) throws Exception {

        DataList<IfaSelfInspectItemManageSql003ResponseModel> res = new DataList<IfaSelfInspectItemManageSql003ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql003(req));
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：自己点検項目情報更新
     * SQLタイプ：update
     * リクエストクラス：IfaSelfInspectItemManageSql004RequestModel
     * レスポンスクラス：IfaSelfInspectItemManageSql004ResponseModel
     *
     * @param req パラメータ
     * @return 更新行数
     * @exception Exception システムエラー
     */
    @Override
    public int updateIfaSelfInspectItemManageSql004(
            IfaSelfInspectItemManageSql004RequestModel req
    ) throws Exception {

        return mapper.updateIfaSelfInspectItemManageSql004(req);
    }



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
    @Override
    public int deleteIfaSelfInspectItemManageSql005(
            IfaSelfInspectItemManageSql005RequestModel req
    ) throws Exception {

        return mapper.deleteIfaSelfInspectItemManageSql005(req);
    }

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
    @Override
    public DataList<IfaSelfInspectItemManageSql006ResponseModel> selectIfaSelfInspectItemManageSql006(
            IfaSelfInspectItemManageSql006RequestModel req
    ) throws Exception {

        DataList<IfaSelfInspectItemManageSql006ResponseModel> res = new DataList<IfaSelfInspectItemManageSql006ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql006(req));
        return res;
    }

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
    @Override
    public DataList<IfaSelfInspectItemManageSql007ResponseModel> selectIfaSelfInspectItemManageSql007(
            IfaSelfInspectItemManageSql007RequestModel req
    ) throws Exception {

        DataList<IfaSelfInspectItemManageSql007ResponseModel> res = new DataList<IfaSelfInspectItemManageSql007ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql007(req));
        return res;
    }

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
    @Override
    public DataList<IfaSelfInspectItemManageSql008ResponseModel> selectIfaSelfInspectItemManageSql008(
            IfaSelfInspectItemManageSql008RequestModel req
    ) throws Exception {

        DataList<IfaSelfInspectItemManageSql008ResponseModel> res = new DataList<IfaSelfInspectItemManageSql008ResponseModel>();

        res.setDataList(mapper.selectIfaSelfInspectItemManageSql008(req));
        return res;
    }

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
    @Override
    public int deleteIfaSelfInspectItemManageSql009(
            IfaSelfInspectItemManageSql009RequestModel req
    ) throws Exception {

        return mapper.deleteIfaSelfInspectItemManageSql009(req);
    }

}
