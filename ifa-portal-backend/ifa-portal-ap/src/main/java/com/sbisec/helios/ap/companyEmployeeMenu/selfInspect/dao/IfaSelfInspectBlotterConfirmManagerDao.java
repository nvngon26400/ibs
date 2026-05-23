package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002ResponseModel;

/**
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 *
 */
public interface IfaSelfInspectBlotterConfirmManagerDao {
    
    /**
     * SQLID：Sql001
     * SQL名：登録年月dropDownLisTデータ取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterConfirmManagerSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterConfirmManagerSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerSql001ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql001()
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
    public DataList<IfaSelfInspectBlotterConfirmManagerSql002ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql002(
            IfaSelfInspectBlotterConfirmManagerSql002RequestModel req) throws Exception;
    
}
