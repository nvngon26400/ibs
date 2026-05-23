package com.sbisec.helios.ap.wholePortal.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002ResponseModel;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaInfoDetailDao {

    /**
     * SQLID：SQL001
     * SQL名：お知らせ既読テーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaInfoDetailSql001RequestModel
     *
     * @param req パラメータ
     * @return 更新行数
     * @exception Exception システムエラー
     */
    public int updateIfaInfoDetailSql001(
            IfaInfoDetailSql001RequestModel req
    ) throws Exception;


    /**
     * SQLID：Sql002
     * SQL名：お知らせ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoDetailSql002RequestModel
     * レスポンスクラス：IfaInfoDetailSql002ResponseModel
     *
     * @param req パラメータ
     * @return お知らせ情報
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoDetailSql002ResponseModel> selectIfaInfoDetailSql002(
            IfaInfoDetailSql002RequestModel req
    ) throws Exception;

}
