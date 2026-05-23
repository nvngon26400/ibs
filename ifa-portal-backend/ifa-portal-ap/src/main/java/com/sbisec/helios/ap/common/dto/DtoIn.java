package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DtoIn {

	@JsonIgnore
	public abstract String getApi();

	@JsonIgnore
	public abstract boolean getRetry();
}