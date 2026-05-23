package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author <author-name>
 2024/05/28 新規作成
 */
@Mapper
public interface IfaResponseStatusUpdateMapper {
    
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
    public List<IfaResponseStatusUpdateSql001ResponseModel> selectIfaResponseStatusUpdateSql001(
        @Param("req") IfaResponseStatusUpdateSql001RequestModel req
        ) throws Exception;
    
    
    /**
     * SqlID：Sql002
     * Sql名：対応状況更新
     * Sqlタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaResponseStatusUpdateSql002(
        @Param("req")  IfaResponseStatusUpdateSql002RequestModel req
        ) throws Exception;
}
