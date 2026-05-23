package com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001ResponseModel;





/**
 * 
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 *
 * @author <author-name>
 2024/06/19 新規作成
 */
@Mapper
public interface IfaOtherFeeDetailMapper {
    
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
    public List<IfaOtherFeeDetailSql001ResponseModel> selectIfaOtherFeeDetailSql001(
        @Param("req") IfaOtherFeeDetailSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
