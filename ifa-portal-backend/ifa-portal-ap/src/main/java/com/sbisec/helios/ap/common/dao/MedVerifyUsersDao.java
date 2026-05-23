package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MedVerifyUser;

/**
 * 認証ユーザ情報
 *
 * @author SCSK
 *
 */
public interface MedVerifyUsersDao {

    /**
     * 認証ユーザ情報取得処理
     *
     * @param userId ユーザID
     * @return 認証ユーザ情報
     * @throws Exception 例外
     */
    public MedVerifyUser getMedVerifyUserInfo(String userId) throws Exception;
    
    /**
     * メールアドレス更新処理
     *
     * @param medVerifyUser 認証ユーザ情報
     * @return 更新結果件数
     * @throws Exception 例外
     */
    public int updateMedVerifyMailAddress(MedVerifyUser medVerifyUser) throws Exception;
}
