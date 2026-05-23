package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql011RequestModel;
import com.sbisec.helios.ap.common.annotation.dao.EtintraMapper;

/**
 * 
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * @author SCSK濱田
 *
 * 2024/04/15 新規作成
 */
@EtintraMapper
public interface IfaSubscriptInputConfirmEtintraMapper {
    
    /**
     * SQLID：Sql011
     * SQL名：ブックビルディング受付テーブルの更新
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql011RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql011ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql011(
        @Param("req")  IfaSubscriptInputConfirmSql011RequestModel req
        ) throws Exception;    
    
}
