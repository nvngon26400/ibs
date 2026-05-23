package com.sbisec.helios.ap.ph1only.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.model.MedGovMenu;
import com.sbisec.helios.ap.ph1only.dao.PreviousSystemMedGovMenuDao;
import com.sbisec.helios.ap.ph1only.dao.mapper.PreviousSystemMedGovMenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * MedGovMenuDaoImpl
 *
 * @author 河口
 *
 */
@Component
public class PreviousSystemMedGovMenuDaoImpl implements PreviousSystemMedGovMenuDao {
    
    @Autowired
    private PreviousSystemMedGovMenuMapper mapper;
    
    @Override
    public List<MedGovMenu> getMedGovMenuList(String userId) throws Exception {
        
        return mapper.getMedGovMenuList(userId);
    }
}
