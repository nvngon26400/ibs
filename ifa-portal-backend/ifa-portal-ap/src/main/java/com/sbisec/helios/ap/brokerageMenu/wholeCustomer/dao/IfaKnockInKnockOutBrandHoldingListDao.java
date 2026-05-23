package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql002ResponseModel;

/**
 * 画面ID：SUB020301_03-02,SUB020301_03-03
 * 画面名：ノックイン銘柄保有一覧,ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/13 新規作成
 */

public interface IfaKnockInKnockOutBrandHoldingListDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：ノックイン銘柄保有一覧取得,ノックアウト銘柄保有一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaKnockInKnockOutBrandHoldingListSql001RequestModel
     * レスポンスクラス：IfaKnockInKnockOutBrandHoldingListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaKnockInKnockOutBrandHoldingListSql001ResponseModel> selectIfaKnockInKnockOutBrandHoldingListSql001(IfaKnockInKnockOutBrandHoldingListSql001RequestModel req)
            throws Exception;

   /**
     * SQLID：Sql002
     * SQL名：ノックイン銘柄保有一覧取得,ノックアウト銘柄保有一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaKnockInKnockOutBrandHoldingListSql002RequestModel
     * レスポンスクラス：IfaKnockInKnockOutBrandHoldingListSql002ResponseModel

     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaKnockInKnockOutBrandHoldingListSql002ResponseModel> selectIfaKnockInKnockOutBrandHoldingListSql002(IfaKnockInKnockOutBrandHoldingListSql002RequestModel req)
            throws Exception;
    
}
