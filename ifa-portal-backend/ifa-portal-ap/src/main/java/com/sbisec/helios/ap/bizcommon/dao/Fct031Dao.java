package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001ResponseModel;

/**
 * 共通関数：FCT031
 * 顧客情報取得
 *
 * @author SCSK
 */
public interface Fct031Dao {
    
    /**
     * 仲介業者顧客口座属性（MEDIATE_USER_ATTRIBUTE_INFO）テーブル等より顧客情報を取得する。
     *
     * @param fct031Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 顧客情報取得に例外が発生した場合
     */
    public List<Fct031Sql001ResponseModel> getCustomerInfo(Fct031Sql001RequestModel fct031Sql001RequestModel)
            throws Exception;
    
}
