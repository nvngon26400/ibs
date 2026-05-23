package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.MedVerifyUsersDao;
import com.sbisec.helios.ap.common.dao.mapper.MedVerifyUsersMapper;
import com.sbisec.helios.ap.common.model.MedVerifyUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 認証ユーザ情報
 *
 * @author SCSK
 *
 */
@Component
public class MedVerifyUsersDaoImpl implements MedVerifyUsersDao {
    
    @Autowired
    protected MedVerifyUsersMapper mapper;
    
    /**
     * 認証ユーザ情報取得処理
     *
     * @param userId ユーザID
     * @return 認証ユーザ情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedVerifyUsersDao#getMedVerifyUserInfo(java.lang.String)
     */
    @Override
    public MedVerifyUser getMedVerifyUserInfo(String userId) throws Exception {
        
        return mapper.getMedVerifyUserInfo(userId);
        
    }
    
    /**
     * メールアドレス更新処理
     *
     * @param medVerifyUser 認証ユーザ情報
     * @return 更新結果件数
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedVerifyUsersDao#updateMedVerifyMailAddress(com.sbisec.helios.ap.common.model.MedVerifyUser)
     */
    @Override
    public int updateMedVerifyMailAddress(MedVerifyUser medVerifyUser) throws Exception {
        
        return mapper.updateMedVerifyMailAddress(medVerifyUser);
    }
}
