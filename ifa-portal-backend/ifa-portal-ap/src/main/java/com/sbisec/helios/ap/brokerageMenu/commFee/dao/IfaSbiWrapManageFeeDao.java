package com.sbisec.helios.ap.brokerageMenu.commFee.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001ResponseModel;





/**
 * 画面ID：SUB020504-01
 * 画面名：SBIラップ管理報酬
 *
 * @author <author-name>
 2024/06/18 新規作成
 *
 */
public interface IfaSbiWrapManageFeeDao {
    
	
    /**
     * SQLID：SQL001
     * SQL名：SBIラップ管理報酬情報データリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaSbiWrapManageFeeSQL001RequestModel
     * レスポンスクラス：IfaSbiWrapManageFeeSQL001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSbiWrapManageFeeSql001ResponseModel> selectIfaSbiWrapManageFeeSql001(IfaSbiWrapManageFeeSql001RequestModel req)
            throws Exception;
    
    
    
    
}
