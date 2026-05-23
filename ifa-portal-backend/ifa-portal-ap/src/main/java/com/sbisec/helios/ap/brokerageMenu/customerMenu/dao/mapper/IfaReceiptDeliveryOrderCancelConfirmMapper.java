package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * 画面ID：SUB0202_0212-09_1
 * 画面名：現引現渡注文取消確認
 *
 * @author SCSK
 2024/05/21 新規作成
 */
@Mapper
public interface IfaReceiptDeliveryOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaReceiptDeliveryOrderCancelConfirmSql001(
            @Param("req") IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaReceiptDeliveryOrderCancelConfirmSql002(
            @Param("req") IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaReceiptDeliveryOrderCancelConfirmSql002b(
            @Param("req") IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：IFA注文番号の取得
     * SQLタイプ：select
     *
     * @return sql003レスポンス
     * @exception Exception error
     */
    public List<IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel> selectIfaReceiptDeliveryOrderCancelConfirmSql003(
            @Param("req") IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel req) throws Exception;
        
}
