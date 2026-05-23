package com.sbisec.helios.ap.faq.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql002ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003ResponseModel;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 *
 */
public interface IfaFaqDao {
    
    /**
     * SQLID：Sql001
     * SQL名：コンテンツ本文取得
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql001RequestModel
     * レスポンスクラス：IfaFaqSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql001ResponseModel> selectIfaFaqSql001(IfaFaqSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：コンテンツリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql002RequestModel
     * レスポンスクラス：IfaFaqSql002ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql002ResponseModel> selectIfaFaqSql002() throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：コンテンツ本文検索
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql003RequestModel
     * レスポンスクラス：IfaFaqSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql003ResponseModel> selectIfaFaqSql003(IfaFaqSql003RequestModel req) throws Exception;
    
}
