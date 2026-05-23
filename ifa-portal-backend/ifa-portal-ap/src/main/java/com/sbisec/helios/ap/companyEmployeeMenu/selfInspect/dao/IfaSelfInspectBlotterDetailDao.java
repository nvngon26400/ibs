package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001ResponseModel;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細
 *
 * @author SCSK
 2024/06/12 新規作成
 *
 */
public interface IfaSelfInspectBlotterDetailDao {
    
    /**
     * SQLID：Sql001
     * SQL名：自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterDetailSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterDetailSql001ResponseModel> selectIfaSelfInspectBlotterDetailSql001(
            IfaSelfInspectBlotterDetailSql001RequestModel req) throws Exception;
    
}
