package com.sbisec.helios.ap.extapi.servicenow.dto;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;

import lombok.Data;

/**
 * ログインID登録 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA005RequestDto {
    
    /** ユーザーID. */
    private String userId;
    
    /** ユーザー名. */
    private String userNm;
    
    /** パスワード. */
    private String password;
    
    /** 権限コード. */
    private String privId;
    
    /** 管理者フラグ. */
    private String governorFlag;
    
    /** メニューリスト. */
    private List<IfaServiceNowMenuDto> menuList;
    
    /** 仲介業者リスト. */
    private List<IfaServiceNowBrokerDto> brokerList;
    
    /** メールアドレス. */
    private String mailAddress;
    
    /** CCSユーザID */
    private String ccsUserId;
    
}
