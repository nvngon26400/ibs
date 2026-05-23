package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.MCodeDao;
import com.sbisec.helios.ap.common.dao.mapper.MCodeMapper;
import com.sbisec.helios.ap.common.model.MCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * コードマスタ
 *
 * @author SCSK
 *
 */
@Component
public class MCodeDaoImpl implements MCodeDao {

    @Autowired
    protected MCodeMapper mapper;
    
    /**
     * コードマスタリスト取得処理
     *
     * @return コードマスタリスト
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MCodeDao#getMCodeList()
     */
    @Override
    public List<MCode> getMCodeList() throws Exception {
        
        return mapper.getMCodeList();
    }
}
