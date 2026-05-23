package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaOtherRemainPowerRestrainInputConfirmMapper {

    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaOtherRemainPowerRestrainInputConfirmSql001(
            @Param("req") IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaOtherRemainPowerRestrainInputConfirmSql002(
            @Param("req") IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel x_req) throws Exception;

}
