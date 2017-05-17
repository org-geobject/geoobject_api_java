package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

public class ParameterJson {

	private String name;
	private String in;
	private Boolean required;
	private String type;
	private List<RefJson> schema = new ArrayList<RefJson>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RefJson> getSchema() {
		return schema;
	}

	public void setSchema(List<RefJson> schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return "ParameterJson [name=" + name + ", in=" + in + ", required="
				+ required + ", type=" + type + ", schema=" + schema + "]";
	}

}
