package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginRepayOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMarginRepayOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCancelConfirmSql002RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0212-07_1
 * 画面名：信用返済注文取消確認
 * 2024/05/27 新規作成
 *
 * @author 宇田川達弥
 */
@Component
public class IfaMarginRepayOrderCancelConfirmDaoImpl extends RowSelectableDao
        implements IfaMarginRepayOrderCancelConfirmDao {
    
    @Autowired
    private IfaMarginRepayOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginRepayOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int insertIfaMarginRepayOrderCancelConfirmSql001(IfaMarginRepayOrderCancelConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaMarginRepayOrderCancelConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql001Sub
     * SQL名：国内株式注文取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMarginRepayOrderCancelConfirmSql001SubResponseModel
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public IfaMarginRepayOrderCancelConfirmSql001SubResponseModel selectIfaMarginRepayOrderCancelConfirmSql001Sub(
            String orderNum, String butenCode, String orderDayTime) throws Exception {
        
        return mapper.selectIfaMarginRepayOrderCancelConfirmSql001Sub(orderNum, butenCode, orderDayTime);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginRepayOrderCancelConfirmSql002(IfaMarginRepayOrderCancelConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderCancelConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエスト
     * @return res 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginRepayOrderCancelConfirmSql002b(IfaMarginRepayOrderCancelConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderCancelConfirmSql002b(req);
    }
    
}
