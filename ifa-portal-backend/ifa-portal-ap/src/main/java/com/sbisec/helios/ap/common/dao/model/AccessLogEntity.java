package com.sbisec.helios.ap.common.dao.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * アクセスログテーブルエンティティクラス。
 * 
 * @author SCSK宮坂
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccessLogEntity extends ModelBase {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 基準日 */
	private String baseDay = null;

	/** 通番 */
	private Long serialNumber = null;

	/** アクセス時間 */
	private Date accessTm = null;

	/** ユーザーID */
	private String userId = null;

	/** ユーザー名 */
	private String userNm = null;

	/** 権限ID */
	private String privId = null;

	/** メニューID */
	private String menuId = null;

	/** 機能名 */
	private String functionNm = null;

	/** 関数名 */
	private String methodNm = null;

	/** アクション内容 */
	private String actionContent = null;

	/** ポジション */
	private String position = null;

	/** パラメータ */
	private String parameters = null;
}
