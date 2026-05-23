package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * 画面ID：SUB0202_0212-02_2
 * 画面名：信用新規注文訂正確認
 *
 * @author SCSK
   2024/04/16 新規作成
 */
@Mapper
public interface IfaMarginNewOrderCorrectConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @return sql001レスポンス
     * @exception Exception error
     */
    public int insertIfaMarginNewOrderCorrectConfirmSql001(
            @Param("req") IfaMarginNewOrderCorrectConfirmSql001RequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @return sql002レスポンス
     * @exception Exception error
     */
    public int updateIfaMarginNewOrderCorrectConfirmSql002(
            @Param("req") IfaMarginNewOrderCorrectConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @return sql002レスポンス
     * @exception Exception error
     */
    public int updateIfaMarginNewOrderCorrectConfirmSql002b(
            @Param("req") IfaMarginNewOrderCorrectConfirmSql002RequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：IFA注文番号の取得
     * SQLタイプ：select
     *
     * @return sql003レスポンス
     * @exception Exception error
     */
    public List<IfaMarginNewOrderCorrectConfirmSql003ResponseModel> selectIfaMarginNewOrderCorrectConfirmSql003(
            @Param("req") IfaMarginNewOrderCorrectConfirmSql003RequestModel req) throws Exception;
    
}
