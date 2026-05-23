package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.common.dto.SaveParameterLogRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * パラメータログサービスインターフェイス。
 *
 * @author SCSK宮坂
 */
public interface ParameterLogService extends Service {
    
    /**
     * パラメータログをテーブルに保管する。
     *
     * @param saveParameterLogRequestDto パラメータログ保管リクエストDTO。
     * @throws Exception 登録に失敗した場合。
     */
    public void saveParameterLog(SaveParameterLogRequestDto saveParameterLogRequestDto) throws Exception;
}
