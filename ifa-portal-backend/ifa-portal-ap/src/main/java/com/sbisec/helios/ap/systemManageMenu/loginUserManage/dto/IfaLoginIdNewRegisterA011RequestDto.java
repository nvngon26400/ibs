package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA011RequestDto {

	/** ログインID. */
	private String loginId;

	/** パスワード. */
	private String password;

	/** ユーザー名. */
	private String userName;

	/** メールアドレス（メールアドレス）. */
	private String mailAddress;

	/** 所属権限コード. */
	private String privId;

	/** 本支店コード. */
	private String branchCode;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者支店コード（数字）. */
	private String subBrokerId;

	/** 担当者コード. */
	private String employeeId;

	/** 担当者名（全角半角）. */
	private String chargeName;

	/** 表示リスト. */
    private List<String> displayList;

}
