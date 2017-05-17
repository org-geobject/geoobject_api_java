package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

public class VerbJson {

	private String verb;
	private String summary;
	private String operationId;
	private List<String> produces = new ArrayList<String>();
	private List<ParameterJson> parameters = new ArrayList<ParameterJson>();
	private List<ResponseJson> responses = new ArrayList<ResponseJson>();

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public List<String> getProduces() {
		return produces;
	}

	public void setProduces(List<String> produces) {
		this.produces = produces;
	}

	public List<ParameterJson> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterJson> parameters) {
		this.parameters = parameters;
	}

	public List<ResponseJson> getResponses() {
		return responses;
	}

	public void setResponses(List<ResponseJson> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "VerbJson [verb=" + verb + ", summary=" + summary
				+ ", operationId=" + operationId + ", produces=" + produces
				+ ", parameters=" + parameters + ", responses=" + responses
				+ "]";
	}

}
