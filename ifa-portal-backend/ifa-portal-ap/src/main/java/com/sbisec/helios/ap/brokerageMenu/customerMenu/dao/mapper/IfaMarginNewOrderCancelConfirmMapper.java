package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
@Mapper
public interface IfaMarginNewOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int insertIfaMarginNewOrderCancelConfirmSql001(
            @Param("req") IfaMarginNewOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql001Sub
     * SQL名：国内株式注文情報取得
     * SQLタイプ：select
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaMarginNewOrderCancelConfirmSql001SubResponseModel selectIfaMarginNewOrderCancelConfirmSql001Sub(
            @Param("orderNum") String orderNum, @Param("butenCode") String butenCode,
            @Param("orderDayTime") String orderDayTime) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginNewOrderCancelConfirmSql002(
            @Param("req") IfaMarginNewOrderCancelConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginNewOrderCancelConfirmSql002b(
        @Param("req") IfaMarginNewOrderCancelConfirmSql002RequestModel req) throws Exception;

}
