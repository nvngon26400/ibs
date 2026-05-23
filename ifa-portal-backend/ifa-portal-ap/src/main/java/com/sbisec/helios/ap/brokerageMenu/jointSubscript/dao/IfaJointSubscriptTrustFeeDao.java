package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001ResponseModel;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
public interface IfaJointSubscriptTrustFeeDao {
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集　信託報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptTrustFeeSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptTrustFeeSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeSql001ResponseModel> selectIfaJointSubscriptTrustFeeSql001(
            IfaJointSubscriptTrustFeeSql001RequestModel req) throws Exception;

}
