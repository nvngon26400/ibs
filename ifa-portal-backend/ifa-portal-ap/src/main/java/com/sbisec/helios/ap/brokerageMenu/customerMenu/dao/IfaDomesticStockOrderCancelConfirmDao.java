package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql002RequestModel;



/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 *  @author 卞智ホ
 */
public interface IfaDomesticStockOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql001ResponseModel
     *
     * @param req SQLに対する入力
     * @return 登録成否
     * @exception Exception systemエラー
     */
    public int insertIfaDomesticStockOrderCancelConfirmSql001(IfaDomesticStockOrderCancelConfirmSql001RequestModel req)
            throws Exception;
    

    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql001ResponseModel
     *
     * @param req SQLに対する入力
     * @return EC受注番号が等しい最新のレコード
     * @exception Exception systemエラー
     */
    public DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> selectIfaDomesticStockOrderCancelConfirmSql001Sub(
            IfaDomesticStockOrderCancelConfirmSql001SubRequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql002ResponseModel
     *
     * @param req SQLに対する入力
     * @return 更新成否
     * @exception Exception systemエラー
     */
    public int updateIfaDomesticStockOrderCancelConfirmSql002(IfaDomesticStockOrderCancelConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     *
     * @param req SQLに対する入力
     * @return 更新成否
     * @exception Exception systemエラー
     */
    public int updateIfaDomesticStockOrderCancelConfirmSql002b(IfaDomesticStockOrderCancelConfirmSql002RequestModel req)
            throws Exception;
    
}
