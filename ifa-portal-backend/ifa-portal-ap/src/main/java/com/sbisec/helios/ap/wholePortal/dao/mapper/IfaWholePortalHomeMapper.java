package com.sbisec.helios.ap.wholePortal.dao.mapper;

import java.util.List;

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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB01-01
 * 画面名：総合ポータル_ホーム

 * @author 池亀緑
 *
 */
@Mapper
public interface IfaWholePortalHomeMapper {

    /**
     * SQLID：Sql001
     * SQL名：顧客アラート情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql001RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql001ResponseModel
     */
    public List<IfaWholePortalHomeSql001ResponseModel> selectIfaWholePortalHomeSql001(
                @Param("req") IfaWholePortalHomeSql001RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：管理者アラート・コンプライアンス通信未対応情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql002RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql002ResponseModel
     */
    public List<IfaWholePortalHomeSql002ResponseModel> selectIfaWholePortalHomeSql002(
            @Param("req") IfaWholePortalHomeSql002RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：管理者アラート・自己点検未対応情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql003RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql003ResponseModel
     */
    public List<IfaWholePortalHomeSql003ResponseModel> selectIfaWholePortalHomeSql003(
            @Param("req") IfaWholePortalHomeSql003RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：カテゴリ一覧
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql004RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql004ResponseModel
     */
    public List<IfaWholePortalHomeSql004ResponseModel> selectIfaWholePortalHomeSql004(
        ) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：本店カテゴリ内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql005RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql005ResponseModel
     */
    public List<IfaWholePortalHomeSql005ResponseModel> selectIfaWholePortalHomeSql005(
            @Param("req") IfaWholePortalHomeSql005RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：本店以外カテゴリ内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql006RequestModel
     * レスポンスクラス：IfaWholePortalHomeSql006ResponseModel
     */
    public List<IfaWholePortalHomeSql006ResponseModel> selectIfaWholePortalHomeSql006(
            @Param("req") IfaWholePortalHomeSql006RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：添付資料情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaWholePortalHomeSql007RequestModel
     * レスポンスクラス：String
     */
    public List<IfaWholePortalHomeSql007ResponseModel> selectIfaWholePortalHomeSql007(
            @Param("req") IfaWholePortalHomeSql007RequestModel req
        ) throws Exception;


}
