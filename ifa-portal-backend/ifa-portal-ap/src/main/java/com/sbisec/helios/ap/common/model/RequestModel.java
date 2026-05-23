package com.sbisec.helios.ap.common.model;

import lombok.Data;

@Data
public class RequestModel<PostBody> {
	private String frameworkSessionId;
	private String authToken;
	
	private PostBody postBody;
	
	@SuppressWarnings("unchecked")
	public RequestModel(String frameworkSessionId, String authToken, Object object) {
		this.frameworkSessionId = frameworkSessionId;
		this.authToken = authToken;
		this.postBody = (PostBody) object;
	}
}
