package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA001ApiRequest {

	/** メニューリスト. */
    @NotEmpty(message = "メニューリスト")
    private List<IfaLoginIdNewRegisterA001ApiRequest_MenuList> menuList;

}
