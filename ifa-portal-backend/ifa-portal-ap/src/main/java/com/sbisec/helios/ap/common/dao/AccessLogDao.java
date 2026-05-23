package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.dao.model.AccessLogEntity;

/**
 * アクセスログテーブルDAOインターフェイス。
 *
 * @author SCSK宮坂
 */
public interface AccessLogDao {
    
    /**
     * レコード挿入。
     *
     * @param accessLogEntity アクセスログテーブルエンティティ。
     * @return 挿入件数。
     * @throws Exception 挿入に失敗した場合。
     */
    public long insert(AccessLogEntity accessLogEntity) throws Exception;
}
