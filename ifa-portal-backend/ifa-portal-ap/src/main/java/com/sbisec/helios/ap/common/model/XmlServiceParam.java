package com.sbisec.helios.ap.common.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class XmlServiceParam implements Serializable {
	@JacksonXmlProperty(localName = "index", isAttribute = true)
	private String index;
	@JacksonXmlProperty(localName = "type", isAttribute = true)
	private Class type;

	@JacksonXmlProperty(localName = "popertyName", isAttribute = true)
	private String popertyName;

	public XmlServiceParam() {
	}
}
