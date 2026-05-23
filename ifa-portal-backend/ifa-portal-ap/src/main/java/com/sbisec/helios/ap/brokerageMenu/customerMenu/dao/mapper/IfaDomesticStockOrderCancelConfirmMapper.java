package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Mapper
public interface IfaDomesticStockOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエストパラメータ
     * @return 登録成否
     * @exception Exception システムエラー
     */
    public int insertIfaDomesticStockOrderCancelConfirmSql001(
            @Param("req")  IfaDomesticStockOrderCancelConfirmSql001RequestModel req
    ) throws Exception;

    
    /**
     * SQLID：Sql001Sub
     * SQL名：発注前の注文登録
     * SQLタイプ：select
     *
     * @param req リクエストパラメータ
     * @return EC受注番号が等しい最新のレコード
     * @exception Exception システムエラー
     */
    public List<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> selectIfaDomesticStockOrderCancelConfirmSql001Sub(
            @Param("req") IfaDomesticStockOrderCancelConfirmSql001SubRequestModel req
    ) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエストパラメータ
     * @return 更新成否
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderCancelConfirmSql002(
            @Param("req")  IfaDomesticStockOrderCancelConfirmSql002RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエストパラメータ
     * @return 更新成否
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderCancelConfirmSql002b(
            @Param("req")  IfaDomesticStockOrderCancelConfirmSql002RequestModel req
    ) throws Exception;

}
