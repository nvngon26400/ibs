package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel;



/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *     2023/11/27 新規作成
 */
@Mapper
public interface IfaDomesticMutualFundOrderCancelConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録（サブクエリ①）
     * SQLタイプ：select
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public List<IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel> selectIfaDomesticMutualFundOrderCancelConfirmSql00101(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録（シーケンスオブジェクトからの通番取得）
     * SQLタイプ：select
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public List<IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel> selectIfaDomesticMutualFundOrderCancelConfirmSql00102(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録（サブクエリ①でレコードが0件の場合）
     * SQLタイプ：insert
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public int insertIfaDomesticMutualFundOrderCancelConfirmSql00101(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録（サブクエリ①でレコードが1件の場合）
     * SQLタイプ：insert
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public int insertIfaDomesticMutualFundOrderCancelConfirmSql00102(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002b(
            @Param("req")  IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req
        ) throws Exception;
    
}
