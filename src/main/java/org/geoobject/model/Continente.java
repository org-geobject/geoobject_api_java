package org.geoobject.model;

public class Continente {

	private String code;
	private String name;

	public String getId() {
		return code;
	}

	public void setId(String id) {
		this.code = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Continente [code=" + code + ", name=" + name + "]";
	}

}
