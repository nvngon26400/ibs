package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginRepayOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMarginRepayOrderConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql003RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0212-04_2
 * 画面名：信用返済注文確認
 * 2024/04/04 新規作成
 *
 * @author 宇田川達弥
 */
@Component
public class IfaMarginRepayOrderConfirmDaoImpL extends RowSelectableDao implements IfaMarginRepayOrderConfirmDao {
    
    @Autowired
    private IfaMarginRepayOrderConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録(国内株式注文)
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginRepayOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaMarginRepayOrderConfirmSql001ResponseModel
     *
     * @param req 注文登録リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int insertIfaMarginRepayOrderConfirmSql001(IfaMarginRepayOrderConfirmSql001RequestModel req)
            throws Exception {
        
        return mapper.insertIfaMarginRepayOrderConfirmSql001(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：発注前の注文登録(信用返済指定建玉)
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginRepayOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaMarginRepayOrderConfirmSql002ResponseModel
     *
     * @param req 注文登録リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int insertIfaMarginRepayOrderConfirmSql002(IfaMarginRepayOrderConfirmSql002RequestModel req)
            throws Exception {
        
        return mapper.insertIfaMarginRepayOrderConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderConfirmSql003RequestModel
     * レスポンスクラス：IfaMarginRepayOrderConfirmSql003ResponseModel
     *
     * @param req 注文更新リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaMarginRepayOrderConfirmSql003(IfaMarginRepayOrderConfirmSql003RequestModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderConfirmSql003(req);
    }

    /**
     * SQLID：Sql003
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginRepayOrderConfirmSql003RequestModel
     *
     * @param req 注文更新リクエスト
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaMarginRepayOrderConfirmSql003b(IfaMarginRepayOrderConfirmSql003RequestModel req)
            throws Exception {
        
        return mapper.updateIfaMarginRepayOrderConfirmSql003b(req);
    }
    
    /**
     * SQLID：Sql004
     * SQL名：IFA注文番号取得
     * SQLタイプ：select
     *
     * @return IFA注文番号
     * @exception Exception 例外が発生した場合
     */
    public String selectIfaMarginRepayOrderConfirmSql004() throws Exception {
        
        return mapper.selectIfaMarginRepayOrderConfirmSql004();
    }
}
