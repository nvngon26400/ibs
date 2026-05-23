package com.sbisec.helios.ap.common.dao.model;

import lombok.Data;

/**
 * CCSログインユーザー情報更新
 *
 * @author SCSK 矢口
 */
@Data
public class CcsLoginSql001RequestModel {
    
    /** CCSパスワード */
    private String ccsUserPw;
    
    /** ユーザ共通情報.ユーザーID */
    private String userId;
    
}
