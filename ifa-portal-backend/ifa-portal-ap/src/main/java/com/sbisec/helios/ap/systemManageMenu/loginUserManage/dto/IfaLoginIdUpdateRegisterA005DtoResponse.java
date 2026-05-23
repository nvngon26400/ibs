package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA005DtoResponse {
    
    /** 担当者名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList;
    
}
