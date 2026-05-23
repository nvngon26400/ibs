package com.sbisec.helios.ap.extapi.servicenow.dto;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowDataList;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowUserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインID検索 レスポンスDto
 *
 * @author SCSK
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IfaServiceNowUserAccountManagerA014ResponseDto extends IfaServiceNowDataList {

    /** ユーザーリスト. */
    private List<IfaServiceNowUserDto> userList;

}
