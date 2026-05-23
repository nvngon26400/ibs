package com.sbisec.helios.ap.ph1only.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.AccControl;

import org.apache.ibatis.annotations.Mapper;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * AccControlMapper
 *
 * @author 河口
 *
 */
@Mapper
public interface PreviousSystemAccControlMapper {
    
    List<AccControl> getAccControl(String userId);
}
