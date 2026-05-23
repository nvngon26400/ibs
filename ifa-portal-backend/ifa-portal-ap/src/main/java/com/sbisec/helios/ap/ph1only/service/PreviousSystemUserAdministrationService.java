package com.sbisec.helios.ap.ph1only.service;

import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.service.Service;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * UserAdministrationService
 *
 * @author 河口
 *
 */
public interface PreviousSystemUserAdministrationService extends Service {
    
    public UserAccount getUserAccount(String userId, AuthCtrlListExclusionSettings settings) throws Exception;
    
}
