package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0212-07_1
 * 画面名：信用返済注文取消確認
 * 2024/05/27 新規作成
 *
 * @author 宇田川達弥
 */
@Mapper
public interface IfaMarginRepayOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int insertIfaMarginRepayOrderCancelConfirmSql001(
            @Param("req") IfaMarginRepayOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql001Sub
     * SQL名：国内株式注文取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMarginRepayOrderCancelConfirmSql001ResponseModel
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaMarginRepayOrderCancelConfirmSql001SubResponseModel selectIfaMarginRepayOrderCancelConfirmSql001Sub(
            String orderNum, String butenCode, String orderDayTime) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginRepayOrderCancelConfirmSql002(
            @Param("req") IfaMarginRepayOrderCancelConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginRepayOrderCancelConfirmSql002b(
            @Param("req") IfaMarginRepayOrderCancelConfirmSql002RequestModel req) throws Exception;
    
}
