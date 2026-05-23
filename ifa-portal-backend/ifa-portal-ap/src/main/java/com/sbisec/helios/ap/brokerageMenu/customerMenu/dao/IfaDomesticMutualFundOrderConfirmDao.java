package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0401-02_2
 * 画面名：国内投信注文確認
 * @author <author-name>
 * 
 * 2024/03/26 新規作成
 *
 */
public interface IfaDomesticMutualFundOrderConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticMutualFundOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaDomesticMutualFundOrderConfirmSql001(IfaDomesticMutualFundOrderConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderConfirmSql002RequestModel
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticMutualFundOrderConfirmSql002(IfaDomesticMutualFundOrderConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderConfirmSql002RequestModel
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticMutualFundOrderConfirmSql002b(IfaDomesticMutualFundOrderConfirmSql002RequestModel req)
            throws Exception;

}
