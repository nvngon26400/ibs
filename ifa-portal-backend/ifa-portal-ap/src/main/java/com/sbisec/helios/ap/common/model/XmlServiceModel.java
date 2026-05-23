package com.sbisec.helios.ap.common.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class XmlServiceModel implements Serializable {
	@JacksonXmlProperty(localName = "url", isAttribute = true)
	private String url;
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String serviceName;
	@JacksonXmlProperty(localName = "methodName", isAttribute = true)
	private String methodName;
//	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlElementWrapper(localName = "params")
	private List<XmlServiceParam> params;
	
	public XmlServiceModel() {
	}
	
	
	public Class<?>[]  getParamTypeArrayList() {
		return params.stream().map(p -> p.getType()).collect(Collectors.toList()).toArray(new Class<?>[params.size()]);
	}
	
	public String getFunctionId() {
		String[] names = {serviceName, methodName};
		return String.join(".", names);
	}
	
}
