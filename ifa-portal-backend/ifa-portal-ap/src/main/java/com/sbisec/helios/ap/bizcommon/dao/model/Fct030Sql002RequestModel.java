package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

@Data
public class Fct030Sql002RequestModel extends ModelBase {
	//ユーザ共通情報.ユーザID
	private String userId;
	
	//ユーザ共通情報.仲介業者支店コード
	private String subBrokerCode;
	
	//ユーザ共通情報.仲介業者コード
	private String brokerCode;
	
	//ユーザ共通情報.権限コード
	private String privId;
}
