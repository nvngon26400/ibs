package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.MedVerifyCodeDao;
import com.sbisec.helios.ap.common.dao.mapper.MedVerifyCodeMapper;
import com.sbisec.helios.ap.common.model.MedVerifyCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 認証コード
 *
 * @author SCSK
 *
 */
@Component
public class MedVerifyCodeDaoImpl implements MedVerifyCodeDao {
    
    @Autowired
    protected MedVerifyCodeMapper mapper;
    
    /**
     * 認証コード取得処理
     *
     * @param userId ユーザID
     * @return 認証コード情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedVerifyCodeDao#getMedVerifyCode(java.lang.String)
     */
    @Override
    public MedVerifyCode getMedVerifyCode(String userId) throws Exception {
        
        return mapper.getMedVerifyCode(userId);
    }
    
    /**
     * 認証コード登録処理
     *
     * @param medVerifyCode 認証コード情報
     * @return 登録結果件数
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedVerifyCodeDao#insertMedVerifyCode(com.sbisec.helios.ap.common.model.MedVerifyCode)
     */
    @Override
    public int insertMedVerifyCode(MedVerifyCode medVerifyCode) throws Exception {
        
        return mapper.insertMedVerifyCode(medVerifyCode);
    }
    
    /**
     * 認証コード削除処理
     *
     * @param medVerifyCode 認証コード情報
     * @return 削除結果件数
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.dao.MedVerifyCodeDao#deleteMedVerifyCode(com.sbisec.helios.ap.common.model.MedVerifyCode)
     */
    @Override
    public int deleteMedVerifyCode(MedVerifyCode medVerifyCode) throws Exception {
        
        return mapper.deleteMedVerifyCode(medVerifyCode);
    }
}
