package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql015RequestModel;
import com.sbisec.helios.ap.common.annotation.dao.EtintraMapper;

import org.apache.ibatis.annotations.Param;


/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE李
 2024/05/14 新規作成
 */
@EtintraMapper
public interface IfaBbApplyCancelConfirmEtintraMapper {
    
    
    
    
    /**
     * SQLID：Sql001
     * SQL名：BB申込情報取消
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql001(
            @Param("req")  IfaBbApplyCancelConfirmSql001RequestModel req
        ) throws Exception;
    
    
    
    /**
     * SQLID：Sql015
     * SQL名：BB申込情報登録
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql015(
            @Param("req")  IfaBbApplyCancelConfirmSql015RequestModel req
        ) throws Exception;
}
