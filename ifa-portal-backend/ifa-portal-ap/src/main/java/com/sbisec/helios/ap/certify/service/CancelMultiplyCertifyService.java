package com.sbisec.helios.ap.certify.service;

import com.sbisec.helios.ap.common.model.MedVerifyCode;
import com.sbisec.helios.ap.common.model.MedVerifyUser;
import com.sbisec.helios.ap.service.Service;

/**
 * 認証サービス
 *
 * @author SCSK
 *
 */
public interface CancelMultiplyCertifyService extends Service {
    
    /**
     * 認証ユーザ情報取得メソッド。
     *
     * @param userId ユーザID
     * @return 認証ユーザ情報
     * @throws Exception 例外
     */
    public MedVerifyUser getVerifyUserInfo(String userId) throws Exception;
    
    /**
     * 認証コード送信メソッド。
     *
     * @param userId ユーザID
     * @param userName ユーザ名
     * @param mailAddress メールアドレス
     * @return 送信結果
     * @throws Exception 例外
     */
    public boolean sendVerifyCode(String userId, String userName, String mailAddress) throws Exception;
    
    /**
     * メールアドレス更新メソッド。
     *
     * @param userId ユーザID
     * @param mailAddress メールアドレス
     * @return 更新結果件数
     * @throws Exception 例外
     */
    public Integer updateMedMailAddress(String userId, String mailAddress) throws Exception;
    
    /**
     * 認証コード情報取得メソッド。
     *
     * @param userId ユーザID
     * @return 認証コード情報
     * @throws Exception 例外
     */
    public MedVerifyCode getVerifyCodeInfo(String userId) throws Exception;
    
}
