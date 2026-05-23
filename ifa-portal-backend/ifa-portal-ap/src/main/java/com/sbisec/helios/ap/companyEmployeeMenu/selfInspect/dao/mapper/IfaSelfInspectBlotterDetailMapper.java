package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細 Mapper
 *
 * @author SCSK
 2024/06/12 新規作成
 */
@Mapper
public interface IfaSelfInspectBlotterDetailMapper {
    
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
    public List<IfaSelfInspectBlotterDetailSql001ResponseModel> selectIfaSelfInspectBlotterDetailSql001(
            @Param("req") IfaSelfInspectBlotterDetailSql001RequestModel req) throws Exception;
    
}
