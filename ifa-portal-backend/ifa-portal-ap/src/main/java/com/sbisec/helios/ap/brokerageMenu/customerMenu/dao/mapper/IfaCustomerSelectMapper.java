package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001SubQueryResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 *
 * @author SCSK
 */
@Mapper
public interface IfaCustomerSelectMapper {

    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql001RequestModel
     * レスポンスクラス：IfaCustomerSelectSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 顧客一覧取得処理で例外が発生した場合
     */
    public List<IfaCustomerSelectSql001ResponseModel> selectIfaCustomerSelectSql001(
            @Param("req") IfaCustomerSelectSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql002RequestModel
     * レスポンスクラス：IfaCustomerSelectSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public List<IfaCustomerSelectSql002ResponseModel> selectIfaCustomerSelectSql002(
            @Param("req") IfaCustomerSelectSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql002RequestModel
     *
     * @param req リクエスト
     * @return 更新行数
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public int selectIfaCustomerSelectSql00201(@Param("req") IfaCustomerSelectSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：挿入
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql002RequestModel
     *
     * @param req リクエスト
     * @return 挿入行数
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public int selectIfaCustomerSelectSql00202(@Param("req") IfaCustomerSelectSql002RequestModel req)
            throws Exception;
    
}
