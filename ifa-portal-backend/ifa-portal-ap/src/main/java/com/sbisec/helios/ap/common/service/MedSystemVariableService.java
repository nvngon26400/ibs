package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.service.Service;

/**
 * 仲介業システム値取得サービス
 *
 * @author SCSK
 *
 */
public interface MedSystemVariableService extends Service {
    
    /**
     * 仲介業システム値取得処理<br/>
     * キャッシュに存在する場合はキャッシュから取得する
     *
     * @param varName 名称
     * @return システム値
     * @throws Exception 例外
     */
    public String getMedSystemVariable(String varName) throws Exception;
    
}
