package com.sbisec.helios.ap.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.common.annotation.dao.CordysTransactional;
import com.sbisec.helios.ap.common.dao.AccessLogDao;
import com.sbisec.helios.ap.common.dao.model.AccessLogEntity;
import com.sbisec.helios.ap.common.dto.SaveParameterLogRequestDto;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.service.ParameterLogService;

/**
 * パラメータログサービス実装クラス。
 *
 * @author SCSK宮坂
 */
@Service("parameterLogService")
public class ParameterLogServiceImpl implements ParameterLogService {
    
    /** アクセスログテーブルDAO */
    @Autowired
    private AccessLogDao accessLogDao;
    
    /**
     * {@inheritDoc}
     */
    @CordysTransactional
    @Override
    public void saveParameterLog(SaveParameterLogRequestDto saveParameterLogRequestDto) throws Exception {
        
        // 挿入用のテーブルエンティティを構築
        AccessLogEntity accessLogEntity = new AccessLogEntity();
        accessLogEntity.setUserId(saveParameterLogRequestDto.getUserId());
        accessLogEntity.setUserNm(saveParameterLogRequestDto.getUserNm());
        accessLogEntity.setMenuId(saveParameterLogRequestDto.getMenuId());
        accessLogEntity.setPrivId(saveParameterLogRequestDto.getPrivId());
        accessLogEntity.setFunctionNm(saveParameterLogRequestDto.getControllerClassNm());
        accessLogEntity.setMethodNm(saveParameterLogRequestDto.getHttpMethodNm());
        accessLogEntity.setActionContent(saveParameterLogRequestDto.getControllerMethodNm());
        accessLogEntity.setPosition(saveParameterLogRequestDto.getPosition());
        accessLogEntity.setParameters(saveParameterLogRequestDto.getParameters());
        
        // アクセスログテーブルに挿入
        long insertCount = accessLogDao.insert(accessLogEntity);
        
        if (insertCount != 1) {
            // 通常起こりえないのでシステムエラーとし、トランザクションはロールバックする
            throw new IfaRuntimeException("errors.systemError");
        }
    }
}
