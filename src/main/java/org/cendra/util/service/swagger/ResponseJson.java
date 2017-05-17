package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

class ResponseJson {

	private String code;
	private String description;
	private List<RefJson> schema = new ArrayList<RefJson>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RefJson> getSchema() {
		return schema;
	}

	public void setSchema(List<RefJson> schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return "ResponseJson [code=" + code + ", description=" + description
				+ ", schema=" + schema + "]";
	}

}
