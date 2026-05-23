package com.sbisec.helios.ap.ph1only.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.MedGovMenu;

import org.apache.ibatis.annotations.Mapper;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * MedGovMenuMapper
 *
 * @author 河口
 *
 */
@Mapper
public interface PreviousSystemMedGovMenuMapper {
    
    List<MedGovMenu> getMedGovMenuList(String userId);
    
}
