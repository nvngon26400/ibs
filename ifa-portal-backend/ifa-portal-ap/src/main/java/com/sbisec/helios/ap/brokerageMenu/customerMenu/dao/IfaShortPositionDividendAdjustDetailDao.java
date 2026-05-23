package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaShortPositionDividendAdjustDetailSqlModel;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
public interface IfaShortPositionDividendAdjustDetailDao {
    
    /**
     * SQLID：Sql001
     * SQL名：売建配当調整金明細取得
     * SQLタイプ：select
     * レスポンスクラス：IfaShortPositionDividendAdjustDetailSqlModel
     *
     * @param customerCode 顧客コード
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public List<IfaShortPositionDividendAdjustDetailSqlModel> selectIfaShortPositionDividendAdjustDetailSql00101(
            String customerCode) throws Exception;
}