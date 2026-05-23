package com.sbisec.helios.ap.common.model;

import lombok.Data;

@Data
public class RequestModelBase {
	private String frameworkSessionId;
	private String authToken;
}
