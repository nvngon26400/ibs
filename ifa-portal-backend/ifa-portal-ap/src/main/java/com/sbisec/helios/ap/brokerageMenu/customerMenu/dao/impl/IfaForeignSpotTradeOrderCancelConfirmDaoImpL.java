package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.Optional;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignSpotTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignSpotTradeOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0301-03_1
 * 画面名：外国現物取引注文取消確認
 * 2024/03/29 新規作成
 *
 * @author 宇田川達弥
 */
@Component
public class IfaForeignSpotTradeOrderCancelConfirmDaoImpL extends RowSelectableDao
        implements IfaForeignSpotTradeOrderCancelConfirmDao {
    
    @Autowired
    private IfaForeignSpotTradeOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：取消発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエストモデル
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    @Override
    public int insertIfaForeignSpotTradeOrderCancelConfirmSql001(
            IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel req) throws Exception {
        
        return mapper.insertIfaForeignSpotTradeOrderCancelConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：取消発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignSpotTradeOrderCancelConfirmSql002ResponseModel
     *
     * @param req リクエストモデル
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    @Override
    public int updateIfaForeignSpotTradeOrderCancelConfirmSql002(
            IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel req) throws Exception {
        
        return mapper.updateIfaForeignSpotTradeOrderCancelConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：委託注文情報を取得
     * SQLタイプ：select
     *
     * @param acceptOrderNo 取引注文番号
     * @return IFA注文サブ番号
     * @exception Exception 例外が発生した場合
     */
    @Override
    public Optional<String> selectIfaForeignSpotTradeOrderCancelConfirmSql003(String acceptOrderNo) throws Exception {
        
        return mapper.selectIfaForeignSpotTradeOrderCancelConfirmSql003(acceptOrderNo);
    }
    
    /**
     * SQLID：Sql004
     * SQL名：委託注文情報を取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel
     *
     * @param acceptOrderNo 取引注文番号
     * @param ifaOrderSubNo IFA注文サブ番号
     * @return レスポンスモデル
     * @exception Exception 例外が発生した場合
     */
    @Override
    public Optional<IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel> selectIfaForeignSpotTradeOrderCancelConfirmSql004(
            String acceptOrderNo, String ifaOrderSubNo) throws Exception {
        
        return mapper.selectIfaForeignSpotTradeOrderCancelConfirmSql004(acceptOrderNo, ifaOrderSubNo);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     *
     * @return IFA注文番号
     * @exception Exception 例外が発生した場合
     */
    @Override
    public String selectIfaForeignSpotTradeOrderCancelConfirmSql005() throws Exception {
        
        return mapper.selectIfaForeignSpotTradeOrderCancelConfirmSql005();
    }
    
}
