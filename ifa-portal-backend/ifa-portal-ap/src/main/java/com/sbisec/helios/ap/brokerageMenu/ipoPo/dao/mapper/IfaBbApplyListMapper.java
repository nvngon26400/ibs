package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql004ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;





/**
 * 画面ID：SUB0204_02-01
 * 画面名：BB申込一覧
 *
 * @author 李
 2024/03/14 新規作成
 */
@Mapper
public interface IfaBbApplyListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：BB申込一覧情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyListSql001RequestModel
     * レスポンスクラス：IfaBbApplyListSql001ResponseModel
     *
     * @param req IfaBbApplyListSql001RequestModel
     * @return IfaBbApplyListSql001ResponseModel
     * @exception Exception sqlエラー
     */
    public List<IfaBbApplyListSql001ResponseModel> selectIfaBbApplyListSql001(
            @Param("req") IfaBbApplyListSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：存在チェック（BB申込訂正・取消入力）
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyListSql002RequestModel
     * レスポンスクラス：IfaBbApplyListSql002ResponseModel
     *
     * @param req IfaBbApplyListSql002RequestModel
     * @return IfaBbApplyListSql002ResponseModel
     * @exception Exception sqlエラー
     */
    public List<IfaBbApplyListSql002ResponseModel> selectIfaBbApplyListSql002(
            @Param("req") IfaBbApplyListSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：存在チェック（募集入力）
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyListSql003RequestModel
     * レスポンスクラス：IfaBbApplyListSql003ResponseModel
     *
     * @param req IfaBbApplyListSql003RequestModel
     * @return IfaBbApplyListSql003ResponseModel
     * @exception Exception sqlエラー
     */
    public List<IfaBbApplyListSql003ResponseModel> selectIfaBbApplyListSql003(
            @Param("req") IfaBbApplyListSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：募集終了時間の取得
     * SQLタイプ：select
     * レスポンスクラス：IfaBbApplyListSql004ResponseModel
     *
     * @return IfaBbApplyListSql004ResponseModel
     * @exception Exception sqlエラー
     */
    public List<IfaBbApplyListSql004ResponseModel> selectIfaBbApplyListSql004() throws Exception;
    
}
