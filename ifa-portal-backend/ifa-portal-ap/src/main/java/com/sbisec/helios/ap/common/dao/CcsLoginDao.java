package com.sbisec.helios.ap.common.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql001RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002ResponseModel;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
public interface CcsLoginDao {
    
    /**
     * SQLID：Sql001
     * SQL名：CCSログインユーザー情報更新
     * SQLタイプ：update
     * リクエストクラス：CcsLoginSql001RequestModel
     * レスポンスクラス：CcsLoginSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateCcsLoginSql001(CcsLoginSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：CCSURL取得
     * SQLタイプ：select
     * リクエストクラス：CcsLoginSql002RequestModel
     * レスポンスクラス：CcsLoginSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<CcsLoginSql002ResponseModel> selectCcsLoginSql002(CcsLoginSql002RequestModel req) throws Exception;
    
}
