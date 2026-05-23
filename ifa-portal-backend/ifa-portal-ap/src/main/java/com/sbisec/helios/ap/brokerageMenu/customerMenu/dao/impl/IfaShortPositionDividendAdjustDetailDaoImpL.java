package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaShortPositionDividendAdjustDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaShortPositionDividendAdjustDetailMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaShortPositionDividendAdjustDetailSqlModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component
public class IfaShortPositionDividendAdjustDetailDaoImpL extends RowSelectableDao
        implements IfaShortPositionDividendAdjustDetailDao {
    
    @Autowired
    private IfaShortPositionDividendAdjustDetailMapper mapper;
    
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
            String customerCode) throws Exception {
        
        return mapper.selectIfaShortPositionDividendAdjustDetailSql00101(customerCode);
    }
}
