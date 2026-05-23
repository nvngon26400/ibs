package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001ResponseModel;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 * @author
 */
@Mapper
public interface IfaDepositWithdrawDetailMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：入出金明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositWithdrawDetailSql001RequestModel
     * レスポンスクラス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaDepositWithdrawDetailSql001ResponseModel> selectIfaDepositWithdrawDetailSql001(
            @Param("req") IfaDepositWithdrawDetailSql001RequestModel req) throws Exception;
    
}
