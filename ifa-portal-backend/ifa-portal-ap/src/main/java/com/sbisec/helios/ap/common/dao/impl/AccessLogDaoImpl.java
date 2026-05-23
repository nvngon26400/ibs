package com.sbisec.helios.ap.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sbisec.helios.ap.common.dao.AccessLogDao;
import com.sbisec.helios.ap.common.dao.mapper.AccessLogMapper;
import com.sbisec.helios.ap.common.dao.model.AccessLogEntity;

/**
 * アクセスログテーブルDAO実装クラス。
 *
 * @author SCSK宮坂
 */
@Repository
public class AccessLogDaoImpl implements AccessLogDao {
    
    /** アクセスログテーブルMapper */
    @Autowired
    private AccessLogMapper accessLogMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long insert(AccessLogEntity accessLogEntity) throws Exception {
        
        return accessLogMapper.insert(accessLogEntity);
    }
}
