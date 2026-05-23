package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001ResponseModel;

/**
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Mapper
public interface IfaContactCorrectHistoryMapper {

    /**
     * SQLID：Sql001
     * SQL名：修正履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectHistorySql001RequestModel
     * レスポンスクラス：IfaContactCorrectHistorySql001ResponseModel
     *
     * @param req {@code IfaContactCorrectHistorySql001RequestModel }
     * @return {@code IfaContactCorrectHistorySql001ResponseModel}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaContactCorrectHistorySql001ResponseModel> selectIfaContactCorrectHistorySql001(
        @Param("req") IfaContactCorrectHistorySql001RequestModel req) throws Exception;
    
}
