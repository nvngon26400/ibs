package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderConfirmRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Mapper
public interface IfaReceiptDeliveryOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return INSERTデータ数
     * @exception Exception 例外処理
     */
    public int insertIfaReceiptDeliveryOrderConfirmSql001(@Param("req") IfaReceiptDeliveryOrderConfirmRequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return INSERTデータ数
     * @exception Exception 例外処理
     */
    public int insertIfaReceiptDeliveryOrderConfirmSql002(
            @Param("req") IfaReceiptDeliveryOrderConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaReceiptDeliveryOrderConfirmSql003(@Param("req") IfaReceiptDeliveryOrderConfirmRequestModel req)
            throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaReceiptDeliveryOrderConfirmSql003b(@Param("req") IfaReceiptDeliveryOrderConfirmRequestModel req)
    throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     *
     * @return String シーケンスオブジェクト
     * @exception Exception 例外処理
     */
    public String selectIfaReceiptDeliveryOrderConfirmSql004() throws Exception;
}
