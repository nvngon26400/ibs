package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql001RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002ResponseModel;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
@Mapper
public interface CcsLoginMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：CCSログインユーザー情報更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateCcsLoginSql001(@Param("req") CcsLoginSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：CCSURL取得
     * SQLタイプ：select
     * リクエストクラス：CcsLoginSql002RequestModel
     * レスポンスクラス：CcsLoginSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<CcsLoginSql002ResponseModel> selectCcsLoginSql002(@Param("req") CcsLoginSql002RequestModel req)
            throws Exception;
    
}
