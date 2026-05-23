package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MedVerifyUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 認証ユーザ情報
 *
 * @author SCSK
 *
 */
@Mapper
public interface MedVerifyUsersMapper {
    
    /**
     * 認証ユーザ情報取得処理
     *
     * @param userId ユーザID
     * @return 認証ユーザ情報
     * @throws Exception 例外
     */
    public MedVerifyUser getMedVerifyUserInfo(@Param("userId") String userId) throws Exception;
    
    /**
     * メールアドレス更新処理
     *
     * @param medVerifyUser 認証ユーザ情報
     * @return 更新結果件数
     * @throws Exception 例外
     */
    public int updateMedVerifyMailAddress(@Param("medVerifyUser") MedVerifyUser medVerifyUser) throws Exception;
    
}
