package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignSpotTradeOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignSpotTradeOrderConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderConfirmSql001RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 * 2024/02/08 新規作成
 *
 * @author 福岡　利基
 */
@Component
public class IfaForeignSpotTradeOrderConfirmDaoImpL extends RowSelectableDao
        implements IfaForeignSpotTradeOrderConfirmDao {
    
    @Autowired
    private IfaForeignSpotTradeOrderConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignSpotTradeOrderConfirmSql001ResponseModel
     *
     * @param req リクエスト
     * @return INSERTデータ数
     * @exception Exception 例外処理
     */
    public int insertIfaForeignSpotTradeOrderConfirmSql001(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaForeignSpotTradeOrderConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が正常の場合
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Success(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.updateIfaForeignSpotTradeOrderConfirmSql002Success(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が異常の場合
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Error(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.updateIfaForeignSpotTradeOrderConfirmSql002Error(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql003RequestModel
     *
     * @return String シーケンスオブジェクト
     * @exception Exception 例外処理
     */
    public String selectIfaForeignSpotTradeOrderConfirmSql003() throws Exception {
        
        return mapper.selectIfaForeignSpotTradeOrderConfirmSql003();
    }
    
}
