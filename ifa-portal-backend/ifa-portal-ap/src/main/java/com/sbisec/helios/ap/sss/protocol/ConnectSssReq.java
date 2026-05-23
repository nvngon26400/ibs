package com.sbisec.helios.ap.sss.protocol;

/**
 * 
 * SSS認証情報取得API Request
 * 
 * @author toma.hayashi
 * @date: 11/07/2023
 */
public class ConnectSssReq  implements ConnectSssBaseRequest{

	public ConnectSssReq() {
	}

	// headerとparameterインスタンス化
	private Header header = new Header();
	private Parameter parameter = new Parameter();

	@SuppressWarnings("unchecked")
	@Override
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public class Header {
		public Header() {
		}
	}

	public class Parameter {
		public Parameter() {
		}

		// ApiKey
		private String apiKey;
		// ユーザーID
		private String userId;

		/**
		 * @return ifaIdentifier
		 */
		public String getApiKey() {
			return apiKey;
		}
		/**
		 * @param apiKey
		 */
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		
		/**
		 * @return userId
		 */
		public String getUserId() {
			return userId;
		}
		/**
		 * @param userId
		 */
		public void setUserId(String userId) {
			
			this.userId = userId;
		}
	}
}
