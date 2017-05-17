package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

class PropertyJson {

	private String name;
	private String type;
	private String format;
	private List<String> enumList = new ArrayList<String>();
	private List<RefJson> items = new ArrayList<RefJson>();

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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public List<String> getEnum() {
		return enumList;
	}

	public void setEnum(List<String> enumList) {
		this.enumList = enumList;
	}

	public List<RefJson> getItems() {
		return items;
	}

	public void setItems(List<RefJson> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PropertyJson [name=" + name + ", type=" + type + ", format="
				+ format + ", enumList=" + enumList + ", items=" + items + "]";
	}
	
	

}
