package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.MedSystemVariableDao;
import com.sbisec.helios.ap.common.dao.mapper.MedSystemVariableMapper;
import com.sbisec.helios.ap.common.model.MedSystemVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 仲介業システム値
 *
 * @author SCSK
 *
 */
@Component
public class MedSystemVariableDaoImpl implements MedSystemVariableDao {
    
    @Autowired
    private MedSystemVariableMapper mapper;
    
    /**
     * 仲介業システム値取得処理
     *
     * @return 仲介業システム値リスト
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedSystemVariableDao#getMedSystemVariableList()
     */
    @Override
    public List<MedSystemVariable> getMedSystemVariableList() throws Exception {
        
        return mapper.getMedSystemVariableList();
    }
    
}
