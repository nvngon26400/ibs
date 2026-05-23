package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.MedSystemVariable;

/**
 * 仲介業システム値
 *
 * @author SCSK
 *
 */
public interface MedSystemVariableDao {
    
    /**
     * 仲介業システム値取得処理
     *
     * @return 仲介業システム値リスト
     * @throws Exception 例外
     */
    public List<MedSystemVariable> getMedSystemVariableList() throws Exception;
    
}
