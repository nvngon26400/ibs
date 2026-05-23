package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001ResponseModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaOtherRemainPowerRestrainInputMapper {

    /**
     * SQLID：Sql001
     * SQL名：その他注文履歴情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherRemainPowerRestrainInputSql001ResponseModel
     * レスポンスクラス：IfaOtherRemainPowerRestrainInputSql001RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaOtherRemainPowerRestrainInputSql001ResponseModel> selectIfaOtherRemainPowerRestrainInputSql001(
            @Param("req") IfaOtherRemainPowerRestrainInputSql001RequestModel x_req) throws Exception;

}
