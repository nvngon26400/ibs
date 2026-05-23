package com.sbisec.helios.ap.wholePortal.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql001ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql002ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql003RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql003ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql004ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql005RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql005ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql006RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql006ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql007RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql007ResponseModel;




/**
 * 画面ID：SUB01-01
 * 画面名：総合ポータル_ホーム

 * @author 池亀緑
 *
 */
public interface IfaWholePortalHomeDao {

    /**
     * SQLID：Sql001
     * SQL名：顧客アラート情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql001RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql001ResponseModel
     */
    public DataList<IfaWholePortalHomeSql001ResponseModel> selectIfaWholePortalHomeSql001(IfaWholePortalHomeSql001RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：管理者アラート・コンプライアンス通信未対応情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql002RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql002ResponseModel
     */
    public DataList<IfaWholePortalHomeSql002ResponseModel> selectIfaWholePortalHomeSql002(IfaWholePortalHomeSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：管理者アラート・自己点検未対応情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql003RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql003ResponseModel
     */
    public DataList<IfaWholePortalHomeSql003ResponseModel> selectIfaWholePortalHomeSql003(IfaWholePortalHomeSql003RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：カテゴリ一覧
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql004RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql004ResponseModel
     */
    public DataList<IfaWholePortalHomeSql004ResponseModel> selectIfaWholePortalHomeSql004()
            throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：本店カテゴリ内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql005RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql005ResponseModel
     */
    public DataList<IfaWholePortalHomeSql005ResponseModel> selectIfaWholePortalHomeSql005(IfaWholePortalHomeSql005RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：本店以外カテゴリ内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql006RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql006ResponseModel
     */
    public DataList<IfaWholePortalHomeSql006ResponseModel> selectIfaWholePortalHomeSql006(IfaWholePortalHomeSql006RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：添付資料情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql007RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql007ResponseModel
     */
    public DataList<IfaWholePortalHomeSql007ResponseModel> selectIfaWholePortalHomeSql007(IfaWholePortalHomeSql007RequestModel req)
            throws Exception;

}
