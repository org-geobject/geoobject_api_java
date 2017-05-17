package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

public class ModelJson {

	private String name;
	private String type;
	private List<PropertyJson> properties = new ArrayList<PropertyJson>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PropertyJson> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyJson> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "ModelJson [name=" + name + ", type=" + type + ", properties="
				+ properties + "]";
	}

}
