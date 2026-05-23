package com.sbisec.helios.ap.extapi.servicenow.dto;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowDataList;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 申請情報項目一覧を取得 レスポンスDto
 *
 * @author SCSK
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IfaServiceNowUserAccountManagerA006ResponseDto extends IfaServiceNowDataList {
    
    /** ユーザーID. */
    private String userId;

    /** ユーザー名. */
    private String userNm;

    /** パスワード. */
    private String password;

    /** 権限コード. */
    private String privId;

    /** メニューリスト. */
    private List<IfaServiceNowMenuDto> menuList;

    /** 仲介業者リスト. */
    private List<IfaServiceNowBrokerDto> brokerList;

    /** 管理者フラグ. */
    private String governorFlag;

    /** CCSユーザーID. */
    private String ccsUserId;

    /** メールアドレス. */
    private String mailAddress; 
}
