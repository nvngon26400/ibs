package com.sbisec.helios.ap.common.dao.mapper;

import com.sbisec.helios.ap.common.model.MedVerifyCode;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 認証コード情報
 *
 * @author SCSK
 *
 */
@Mapper
public interface MedVerifyCodeMapper {
    
    /**
     * 認証コード情報取得
     *
     * @param userId ユーザID
     * @return 認証コード情報
     * @throws Exception 例外
     */
    public MedVerifyCode getMedVerifyCode(@Param("userId") String userId) throws Exception;
    
    /**
     * 認証コード情報登録
     *
     * @param medVerifyCode 認証コード情報
     * @return 登録結果件数
     * @throws Exception 例外
     */
    public int insertMedVerifyCode(@Param("medVerifyCode") MedVerifyCode medVerifyCode) throws Exception;
    
    /**
     * 認証コード情報削除
     *
     * @param medVerifyCode 認証コード情報
     * @return 削除結果件数
     * @throws Exception 例外
     */
    public int deleteMedVerifyCode(@Param("medVerifyCode") MedVerifyCode medVerifyCode) throws Exception;
    
}
