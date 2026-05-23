package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0208-03_2
 * 画面名：国内株式注文訂正確認
 *
 * @author SCSK 矢口
 *
 *      2024/04/16 新規作成
 */
@Mapper
public interface IfaDomesticStockOrderCorrectConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req SQL001リクエスト
     * @return 挿入件数
     * @exception Exception 例外
     */
    public int insertIfaDomesticStockOrderCorrectConfirmSql001(
            @Param("req") IfaDomesticStockOrderCorrectConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req SQL002リクエスト
     * @return 更新件数
     * @exception Exception 例外
     */
    public int updateIfaDomesticStockOrderCorrectConfirmSql002(
            @Param("req") IfaDomesticStockOrderCorrectConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req SQL002リクエスト
     * @return 更新件数
     * @exception Exception 例外
     */
    public int updateIfaDomesticStockOrderCorrectConfirmSql002b(
            @Param("req") IfaDomesticStockOrderCorrectConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：シーケンスオブジェクトの取得
     * SQLタイプ：select
     *
     * @param req SQL003リクエスト
     * @return 同じEC受注番号のデータ
     * @exception Exception 例外
     */
    public List<IfaDomesticStockOrderCorrectConfirmSql003ResponseModel> selectIfaDomesticStockOrderCorrectConfirmSql003(
            @Param("req") IfaDomesticStockOrderCorrectConfirmSql003RequestModel req) throws Exception;
    
}
