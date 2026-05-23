package com.sbisec.helios.ap.sss;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sbisec.helios.ap.athena.annotation.SetAttributeName;
import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.broker.OkHttpBaseRequest;
import com.sbisec.helios.ap.sss.util.SssConnectionException;

import okhttp3.MediaType;

/**
 * <p>
 * Description:OkHttpRequest
 * </p>
 *
 * @author toma.hayashi
 * @date 01/26/2024
 */
public class SssOkHttpRequest implements OkHttpBaseRequest{

	public interface CONTENT_TYPE {
		MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
		MediaType MEDIA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
	}

	private ObjectMapper objectMapper;

	public SssOkHttpRequest() {
		contentType = CONTENT_TYPE.MEDIA_TYPE_JSON;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
	}

	private String url;
	private MediaType contentType;
	private Map<String, Object> headers;
	private Map<String, Object> parameters;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MediaType getContentType() {
		return contentType;
	}

	public void setContentType(MediaType contentType) {
		this.contentType = contentType;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	//SSS-APIでは、Headerの指定がないため、実際は使用されない。
	public void setHeaders(Object obj) throws SssConnectionException {
		try {
			this.headers = objectMapper.readValue(objectMapper.writeValueAsString(obj),
					new TypeReference<Map<String, Object>>() {
					});

			dealHeaderAnnotation(obj);
		} catch (Exception e) {
			throw new SssConnectionException(e);
		}
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Object obj) throws SssConnectionException {
		try {
			objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
			this.parameters = objectMapper.readValue(objectMapper.writeValueAsString(obj),
					new TypeReference<Map<String, Object>>() {
					});

			dealParamAnnotation(obj);
		} catch (Exception e) {
			throw new SssConnectionException(e);
		}
	}

	private void dealHeaderAnnotation(Object obj) {

		if (null != obj && null != this.headers && !this.headers.isEmpty()) {
			Class<? extends Object> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (!this.headers.containsKey(f.getName())) {
					continue;
				}
				// SetAttributeName deal
				SetAttributeName san = f.getDeclaredAnnotation(SetAttributeName.class);
				if (null == san) {
					continue;
				}
				this.headers.put(san.name(), this.headers.remove(f.getName()));
			}
		}
	}

	private void dealParamAnnotation(Object obj) {

		if (null != obj && null != this.parameters && !this.parameters.isEmpty()) {
			Class<? extends Object> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (!this.parameters.containsKey(f.getName())) {
					continue;
				}
				// UrlParm deal
				UrlParm up = f.getDeclaredAnnotation(UrlParm.class);
				if (null != up) {
					this.url = this.url.replace("{" + up.name() + "}",
							String.valueOf(this.parameters.get(f.getName())));
					this.parameters.remove(f.getName());
				}
				// SetAttributeName deal
				SetAttributeName san = f.getDeclaredAnnotation(SetAttributeName.class);
				if (null != san && this.parameters.containsKey(f.getName())) {
					this.parameters.put(san.name(), this.parameters.remove(f.getName()));
				}
			}
		}
	}
}
