package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel;





/**
 * 
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author <author-name>
 2024/05/23 新規作成
 */
@Mapper
public interface IfaJpyAmountUnpaidOverdraftAlertListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：預り金赤残顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel
     * レスポンスクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel> selectIfaJpyAmountUnpaidOverdraftAlertListSql001(
        @Param("req") IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
