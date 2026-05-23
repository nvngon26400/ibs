package com.sbisec.helios.ap.safe.service;

import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;

public interface SafeAccountService {

    /**
     * ポイント_積立買付利用設定取得API 
     * POST:/safe/account/account/point/get_reserve_buy_setting
     * 
     */
    public GetAccountPointGetReserveBuySettingRes getReserveBuySetting(GetAccountPointGetReserveBuySettingReq req)
            throws Exception;
}
