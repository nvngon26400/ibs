package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001ResponseModel;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 出金・出金入力
 * 
 * @author xin.huang
 *
 */
@Mapper
public interface IfaWithdrawInputMapper {

    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエスト：IfaWithdrawInputSql001RequestModel
     * レスポンス：IfaWithdrawInputSql001ResponseModel
     *
     * @param req {@code IfaWithdrawInputSql001RequestModel }
     * @return {@code List<IfaWithdrawInputSql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaWithdrawInputSql001ResponseModel> selectIfaWithdrawInputSql001(
            @Param("req") IfaWithdrawInputSql001RequestModel req) throws Exception;

}
