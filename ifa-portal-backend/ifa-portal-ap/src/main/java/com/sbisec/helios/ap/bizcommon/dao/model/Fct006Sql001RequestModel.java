package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * 利用者顧客参照権限
 *
 * @author BASE 高
 *
 */
@Data
public class Fct006Sql001RequestModel {
	private String butenCode; // 部店コード
	private String accountNumber; // 口座番号
}
