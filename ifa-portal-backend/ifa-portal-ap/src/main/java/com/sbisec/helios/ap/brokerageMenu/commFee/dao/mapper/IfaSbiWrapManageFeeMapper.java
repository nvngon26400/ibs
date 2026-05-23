package com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB020504-01
 * 画面名：SBIラップ管理報酬
 *
 * @author <author-name>
 2024/06/18 新規作成
 */
@Mapper
public interface IfaSbiWrapManageFeeMapper {
    
    /**
     * SqlID：Sql001
     * Sql名：SBIラップ管理報酬情報データリスト取得
     * Sqlタイプ：select
     * リクエストクラス：IfaSbiWrapManageFeeSql001RequestModel
     * レスポンスクラス：IfaSbiWrapManageFeeSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSbiWrapManageFeeSql001ResponseModel> selectIfaSbiWrapManageFeeSql001(
            @Param("req") IfaSbiWrapManageFeeSql001RequestModel req) throws Exception;
    
}
