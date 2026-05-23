package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * 紐付け共募支店コード取得のリクエスト
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 *
 */
@Data
public class Fct041Sql001RequestModel {

	// ユーザーID
	private String userId;

	// 仲介業者コード
	private String brokerId;

	// 支店コード
	private String subBrokerId;

}
