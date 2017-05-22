package org.cendra.commons.util.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceMetaData {

//	private String id;
	private String verb;
	private String url;
	private Map<String, ParamMetaData> requestParams = new HashMap<String, ParamMetaData>();
	private String requestContentType = "application/json";
	private Boolean responseBodyIsList;

	public String getId() {
		return "[" + verb + "] " + url;
	}

	public void setId(String id) {
//		this.id = id;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, ParamMetaData> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, ParamMetaData> requestParams) {
		this.requestParams = requestParams;
	}

	public String getRequestContentType() {
		return requestContentType;
	}

	public void setRequestContentType(String requestContentType) {
		this.requestContentType = requestContentType;
	}

	public Boolean getResponseBodyIsList() {
		return responseBodyIsList;
	}

	public void setResponseBodyIsList(Boolean responseBodyIsList) {
		this.responseBodyIsList = responseBodyIsList;
	}

}
