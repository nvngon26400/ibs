package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql002RequestModel;

/**
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author <author-name>
 2024/05/28 新規作成
 *
 */
public interface IfaResponseStatusUpdateDao {
    
	
    /**
     * SqlID：Sql001
     * Sql名：対応状況取得
     * Sqlタイプ：select
     * リクエストクラス：IfaResponseStatusUpdateSql001RequestModel
     * レスポンスクラス：IfaResponseStatusUpdateSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateSql001ResponseModel> selectIfaResponseStatusUpdateSql001(IfaResponseStatusUpdateSql001RequestModel req)
            throws Exception;
    
    
    /**
     * SqlID：Sql002
     * Sql名：対応状況更新
     * Sqlタイプ：update
     * リクエストクラス：IfaResponseStatusUpdateSql002RequestModel
     * レスポンスクラス：IfaResponseStatusUpdateSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaResponseStatusUpdateSql002(IfaResponseStatusUpdateSql002RequestModel req)
            throws Exception;
}
