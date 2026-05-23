package com.sbisec.helios.ap.ph1only.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.model.AccControl;
import com.sbisec.helios.ap.ph1only.dao.PreviousSystemAccControlDao;
import com.sbisec.helios.ap.ph1only.dao.mapper.PreviousSystemAccControlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * AccControlDaoImpl
 *
 * @author 河口
 *
 */
@Component
public class PreviousSystemAccControlDaoImpl implements PreviousSystemAccControlDao {
    
    @Autowired
    protected PreviousSystemAccControlMapper mapper;
    
    @Override
    public List<AccControl> getAccControl(String userId) throws Exception {
        
        return mapper.getAccControl(userId);
    }
}
