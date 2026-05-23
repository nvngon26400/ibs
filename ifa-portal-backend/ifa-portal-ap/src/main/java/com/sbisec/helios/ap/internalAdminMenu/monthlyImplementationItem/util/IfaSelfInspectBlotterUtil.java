package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util;

import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;

import org.springframework.stereotype.Component;

/**
 * 自己点検記録簿用Utility
 *
 * @author SCSK丹波
 */
@Component
public class IfaSelfInspectBlotterUtil {
    
    /** 仲介業者コード0000 */
    private static final String BROKER_CODE_ZERO = "0000";
    
    /**
     * 仲介業者コードを取得する。
     *
     * @param userAccount ユーザ共通情報
     * @return ユーザ共通情報.権限コード = '1'（SBI証券本店）または、'2'（SBI証券支店）の場合: "0000"<br/>上記以外の場合: ユーザ共通情報.仲介業者コード
     */
    public String editBrokerCode(UserAccount userAccount) {
        
        String privId = userAccount.getPrivId();
        // 仲介業者コードのセット
        if (PrivId.HEAD_OFFICE.getId().equals(privId) || PrivId.BRANCH.getId().equals(privId)) {
            // ユーザ共通情報.権限コード = '1'（SBI証券本店）または、'2'（SBI証券支店）の場合
            return BROKER_CODE_ZERO;
            
        } else {
            // ユーザ共通情報.権限コード = '1'（SBI証券本店）または、'2'（SBI証券支店）以外の場合
            return userAccount.getBrokerCode();
        }
    }
}
