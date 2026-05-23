package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginNewOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMarginNewOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql002RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
@Component
public class IfaMarginNewOrderCancelConfirmDaoImpL extends RowSelectableDao
        implements IfaMarginNewOrderCancelConfirmDao {
    
    @Autowired
    private IfaMarginNewOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginNewOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public int insertIfaMarginNewOrderCancelConfirmSql001(IfaMarginNewOrderCancelConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaMarginNewOrderCancelConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql001Sub
     * SQL名：国内株式注文取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMarginNewOrderCancelConfirmSql001SubResponseModel
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public IfaMarginNewOrderCancelConfirmSql001SubResponseModel selectIfaMarginNewOrderCancelConfirmSql001Sub(
            String orderNum, String butenCode, String orderDayTime) throws Exception {
        
        return mapper.selectIfaMarginNewOrderCancelConfirmSql001Sub(orderNum, butenCode, orderDayTime);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaMarginNewOrderCancelConfirmSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public int updateIfaMarginNewOrderCancelConfirmSql002(IfaMarginNewOrderCancelConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaMarginNewOrderCancelConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginNewOrderCancelConfirmSql002b(IfaMarginNewOrderCancelConfirmSql002RequestModel req)
            throws Exception {
        return mapper.updateIfaMarginNewOrderCancelConfirmSql002b(req);
    }

}
