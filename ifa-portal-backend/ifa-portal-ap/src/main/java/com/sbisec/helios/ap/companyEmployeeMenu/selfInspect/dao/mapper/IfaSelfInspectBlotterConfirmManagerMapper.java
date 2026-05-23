package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
@Mapper
public interface IfaSelfInspectBlotterConfirmManagerMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：登録年月dropDownLisTデータ取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterConfirmManagerSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterConfirmManagerSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterConfirmManagerSql001ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql001()
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterConfirmManagerSql002RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterConfirmManagerSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSelfInspectBlotterConfirmManagerSql002ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql002(
            @Param("req") IfaSelfInspectBlotterConfirmManagerSql002RequestModel req) throws Exception;
    
}
