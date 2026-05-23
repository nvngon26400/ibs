package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaShortPositionDividendAdjustDetailSqlModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Mapper
public interface IfaShortPositionDividendAdjustDetailMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：売建配当調整金明細取得
     * SQLタイプ：select
     *
     * @param customerCode 顧客コード
     * @return レスポンス
     * @exception exception 例外が発生した場合
     */
    public List<IfaShortPositionDividendAdjustDetailSqlModel> selectIfaShortPositionDividendAdjustDetailSql00101(
            @Param("customerCode") String customerCode) throws Exception;
}
