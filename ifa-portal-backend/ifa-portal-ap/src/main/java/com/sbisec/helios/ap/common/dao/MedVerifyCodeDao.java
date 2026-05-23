package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MedVerifyCode;

/**
 * 認証コード
 *
 * @author SCSK
 *
 */
public interface MedVerifyCodeDao {
    
    /**
     * 認証コード取得処理
     *
     * @param userId ユーザID
     * @return 認証コード情報
     * @throws Exception 例外
     */
    public MedVerifyCode getMedVerifyCode(String userId) throws Exception;
    
    /**
     * 認証コード登録処理
     *
     * @param medVerifyCode 認証コード情報
     * @return 登録結果件数
     * @throws Exception 例外
     */
    public int insertMedVerifyCode(MedVerifyCode medVerifyCode) throws Exception;
    
    /**
     * 認証コード削除処理
     *
     * @param medVerifyCode 認証コード情報
     * @return 削除結果件数
     * @throws Exception 例外
     */
    public int deleteMedVerifyCode(MedVerifyCode medVerifyCode) throws Exception;
}
