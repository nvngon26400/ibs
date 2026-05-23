package com.sbisec.helios.ap.common.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JacksonXmlRootElement(localName = "services")
public class XmlServiceTopElement implements Serializable {
	
	@JacksonXmlProperty(localName = "service")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XmlServiceModel> services;
	public XmlServiceTopElement() {
	}
}
