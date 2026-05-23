package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel;

/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *     2023/11/27 新規作成
 *
 */
public interface IfaDomesticMutualFundOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel insertIfaDomesticMutualFundOrderCancelConfirmSql001(
            IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002(
            IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req) throws Exception;
            
    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002b(
            IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req) throws Exception;
    
}
