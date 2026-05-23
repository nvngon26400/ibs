package com.sbisec.helios.ap.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.CcsLoginDao;
import com.sbisec.helios.ap.common.dao.mapper.CcsLoginMapper;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql001RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002ResponseModel;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
@Component
public class CcsLoginDaoImpL extends RowSelectableDao implements CcsLoginDao {
    
    @Autowired
    private CcsLoginMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：CCSログインユーザー情報更新
     * SQLタイプ：update
     * リクエストクラス：CcsLoginSql001RequestModel
     * レスポンスクラス：CcsLoginSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateCcsLoginSql001(CcsLoginSql001RequestModel req) throws Exception {
        
        return mapper.updateCcsLoginSql001(req);
    }
    
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
    public DataList<CcsLoginSql002ResponseModel> selectCcsLoginSql002(CcsLoginSql002RequestModel req) throws Exception {
        
        DataList<CcsLoginSql002ResponseModel> res = new DataList<CcsLoginSql002ResponseModel>();
        
        res.setDataList(mapper.selectCcsLoginSql002(req));
        return res;
    }
    
}
