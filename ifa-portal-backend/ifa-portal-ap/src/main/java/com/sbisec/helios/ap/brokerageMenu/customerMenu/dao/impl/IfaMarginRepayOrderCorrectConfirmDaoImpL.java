package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginRepayOrderCorrectConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMarginRepayOrderCorrectConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCorrectConfirmSqlModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文訂正確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component
public class IfaMarginRepayOrderCorrectConfirmDaoImpL extends RowSelectableDao
        implements IfaMarginRepayOrderCorrectConfirmDao {
    
    @Autowired
    private IfaMarginRepayOrderCorrectConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録(国内株式注文)
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginRepayOrderCorrectConfirmSqlModel
     * レスポンスクラス：int
     *
     * @param req 注文登録リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int insertIfaMarginRepayOrderCorrectConfirmSql00101(IfaMarginRepayOrderCorrectConfirmSqlModel req)
            throws Exception {
        
        return mapper.insertIfaMarginRepayOrderCorrectConfirmSql00101(req);
    }
    
    /**
     * SQLID：Sql001 サブクエリ①
     * SQL名：発注前の注文登録(信用返済指定建玉)
     * SQLタイプ：select
     * レスポンスクラス：IfaMarginRepayOrderCorrectConfirmSqlModel
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public IfaMarginRepayOrderCorrectConfirmSqlModel selectIfaMarginRepayOrderCorrectConfirmSql00102(String orderNum,
            String butenCode, String orderDayTime) throws Exception {
        
        return mapper.selectIfaMarginRepayOrderCorrectConfirmSql00102(orderNum, butenCode, orderDayTime);
    }

    /**
     * SQLID：Sql001　サブクエリ②
     * SQL名：IFA注文番号取得
     * SQLタイプ：select
     *
     * @return IFA注文番号
     * @exception Exception 例外が発生した場合
     */
    public String selectIfaMarginRepayOrderCorrectConfirmSql00103() throws Exception {
        
        return mapper.selectIfaMarginRepayOrderCorrectConfirmSql00103();
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderCorrectConfirmSqlModel
     * レスポンスクラス：int
     *
     * @param req 注文更新リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaMarginRepayOrderCorrectConfirmSql002(IfaMarginRepayOrderCorrectConfirmSqlModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderCorrectConfirmSql002(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderCorrectConfirmSqlModel
     * レスポンスクラス：int
     *
     * @param req 注文更新リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaMarginRepayOrderCorrectConfirmSql002b(IfaMarginRepayOrderCorrectConfirmSqlModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderCorrectConfirmSql002b(req);
    }
    
}
