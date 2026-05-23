package com.sbisec.helios.ap.brokerageMenu.commFee.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015ResponseModel;


/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaCommFeeDao {

    /**
     * SQLID：SQL001
     * SQL名：手数料・報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql001To012RequestModel
     * レスポンスクラス：IfaCommFeeSql001To012ResponseModel
     *
     * @param req パラメータ
     * @return 手数料・報酬一覧
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeSql001To012ResponseModel> selectIfaCommFeeSql001To012(
            IfaCommFeeSql001To012RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql015
     * SQL名：SBIラップ管理報酬サービス表示制御情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql015RequestModel
     * レスポンスクラス：IfaCommFeeSql015ResponseModel
     *
     * @param req パラメータ
     * @return SBIラップ管理報酬サービス表示制御情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeSql015ResponseModel> selectIfaCommFeeSql015(
            IfaCommFeeSql015RequestModel req
    ) throws Exception;

}
