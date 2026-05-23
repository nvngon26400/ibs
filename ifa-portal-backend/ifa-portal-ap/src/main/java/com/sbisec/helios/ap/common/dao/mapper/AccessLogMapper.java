package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.annotation.dao.CordysMapper;
import com.sbisec.helios.ap.common.dao.model.AccessLogEntity;

/**
 * アクセスログテーブルMapperインターフェイス。
 *
 * @author SCSK宮坂
 */
@CordysMapper
public interface AccessLogMapper {
    
    /**
     * レコード挿入。
     *
     * @param accessLogEntity アクセスログテーブルエンティティ。
     * @return 挿入件数。
     * @throws Exception 挿入に失敗した場合。
     */
    public long insert(AccessLogEntity accessLogEntity) throws Exception;
}
