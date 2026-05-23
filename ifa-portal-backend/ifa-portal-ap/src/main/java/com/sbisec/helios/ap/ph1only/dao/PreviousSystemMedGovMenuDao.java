package com.sbisec.helios.ap.ph1only.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.MedGovMenu;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * MedGovMenuDao
 *
 * @author 河口
 *
 */
public interface PreviousSystemMedGovMenuDao {
    
    List<MedGovMenu> getMedGovMenuList(String userId) throws Exception;
    
}
