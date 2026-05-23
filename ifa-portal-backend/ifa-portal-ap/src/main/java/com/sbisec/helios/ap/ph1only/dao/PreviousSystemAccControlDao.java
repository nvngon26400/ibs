package com.sbisec.helios.ap.ph1only.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.AccControl;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * AccControlDao
 *
 * @author 河口
 *
 */
public interface PreviousSystemAccControlDao {
    
    List<AccControl> getAccControl(String userId) throws Exception;
}
