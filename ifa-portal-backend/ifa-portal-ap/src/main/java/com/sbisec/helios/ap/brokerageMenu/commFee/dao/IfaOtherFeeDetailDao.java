package com.sbisec.helios.ap.brokerageMenu.commFee.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001ResponseModel;





/**
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 *
 * @author <author-name>
 2024/06/19 新規作成
 *
 */
public interface IfaOtherFeeDetailDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：その他報酬詳細一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherFeeDetailSql001RequestModel
     * レスポンスクラス：IfaOtherFeeDetailSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOtherFeeDetailSql001ResponseModel> selectIfaOtherFeeDetailSql001(IfaOtherFeeDetailSql001RequestModel req)
            throws Exception;
    
    
    
    
}
